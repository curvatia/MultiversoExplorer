package com.example.multiversoexplorer.ui.home;

import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import com.example.multiversoexplorer.ui.dashboard.DashboardViajesRV;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<List<Long>> listaFavoritos;
    private final FirebaseAuth mAuth;
    private MutableLiveData<String> mText;
    private MutableLiveData<List<HomeViajesRV>> listaViajes;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("FRAGMENT DE VIAJES EN CARDVIEW Y ELEGIR FAVORITOS");
        listaViajes = new MutableLiveData<>(new ArrayList<>());
        try {
            String url = "https://www.multiversoexplorer.com/wp-json/wp/v2/trip";
            new HttpAsyncTask().execute(url);
        } catch (Exception e) {}

        listaFavoritos = new MutableLiveData<List<Long>>(new ArrayList<>());
        mAuth = FirebaseAuth.getInstance();
        mAuth.addAuthStateListener(new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser()!=null) {
                    observarFavoritos();
                }
            }
        });
    }

    public LiveData<String> getText() {
        return mText;
    }
    public MutableLiveData<List<HomeViajesRV>> getListaViajes() {
        return listaViajes;
    }
    public MutableLiveData<List<Long>> getListaFavoritos() {return listaFavoritos;}

    public void observarFavoritos(){
        DatabaseReference reference = FirebaseDatabase.getInstance()
                .getReference("userdata/"+ mAuth.getCurrentUser().getUid()+"/favoritos");
        reference.addValueEventListener(new ValueEventListener() {
            private ValueEventListener listener = this;
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Long> datos = (List<Long>) snapshot.getValue();
                listaFavoritos.setValue(datos);
                List<HomeViajesRV> viajesLocal = listaViajes.getValue();
                viajesLocal.stream()
                    .forEach(homeViajesRV -> {
                        if(datos.contains(homeViajesRV.getId()))
                            homeViajesRV.setFavorito(true);
                    });
                listaViajes.setValue(viajesLocal);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });
    }

    public void publicarFavorito(HomeViajesRV viaje) {
        DatabaseReference reference = FirebaseDatabase.getInstance()
                .getReference("userdata/"+ mAuth.getCurrentUser().getUid()+"/favoritos");
        List<Long> favsLocal = listaFavoritos.getValue();
        if(viaje.isEsFavorito()){
            if(!favsLocal.contains(viaje.getId()))
                favsLocal.add(viaje.getId());
        } else {
            if(favsLocal.contains(viaje.getId()))
                favsLocal.remove(favsLocal.indexOf(viaje.getId()));
        }
        reference.setValue(favsLocal);
    }

    private class HttpAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            try {
                HttpURLConnection urlConnection = (HttpURLConnection)
                        new URL(urls[0]).openConnection();
                urlConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                urlConnection.setDoInput(true);
                BufferedReader bis = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                String res = bis.lines().collect(Collectors.joining("\n"));
                bis.close();
                return res;
            } catch (Exception e) {}
            return "";
        }

        @Override
        protected void onPostExecute(String result) {
            try {
                ArrayList<HomeViajesRV> lista = new ArrayList<>();
                JSONArray jsonArray = new JSONArray(result);
                for (int i = 0; i < jsonArray.length(); i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    try {
                        Long longId = Long.valueOf(jsonObject.opt("id").toString());
                        String title = jsonObject.getJSONObject("title").optString("rendered");
                        String informacion = jsonObject.getJSONObject("excerpt").optString("rendered");
                        String content = jsonObject.getJSONObject("content").optString("rendered");
                        String urlImg = jsonObject.getJSONObject("featured_image").optString("file");
                        String precio = jsonObject.optString("price");
                        String duracion = jsonObject.getJSONObject("duration").optString("days");
                        lista.add(
                                new HomeViajesRV(
                                        longId,
                                        title,
                                        informacion,
                                        content,
                                        precio,
                                        duracion,
                                        "https://www.multiversoexplorer.com/wp-content/uploads/" + urlImg));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                listaViajes.setValue(lista);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}