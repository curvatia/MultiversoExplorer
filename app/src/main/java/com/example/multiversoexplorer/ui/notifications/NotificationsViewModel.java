package com.example.multiversoexplorer.ui.notifications;

import android.os.AsyncTask;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.multiversoexplorer.adapter.ReservasAdapter;
import com.example.multiversoexplorer.ui.dashboard.DashboardViajesRV;
import com.example.multiversoexplorer.ui.home.HomeFragment;
import com.example.multiversoexplorer.ui.home.HomeViajesRV;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class NotificationsViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private MutableLiveData<List<Long>> listaFavoritos;
    private ConcurrentHashMap<Long, HomeViajesRV> listaViajes;

    private FirebaseAuth mAuth;
    private DatabaseReference miDatabaseReference = FirebaseDatabase.getInstance().getReference();

    public NotificationsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("FRAGMENT DE VIAJES FAVORITOS DESDE HOME");
        listaFavoritos = new MutableLiveData<List<Long>>(new ArrayList<>());
        listaViajes = new ConcurrentHashMap<>();

        mAuth = FirebaseAuth.getInstance();
        mAuth.addAuthStateListener(new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser()!=null) {
                    observarIdsFavoritos();
                }
            }
        });

        pedirJSON();
    }

    private void observarIdsFavoritos(){
        FirebaseDatabase.getInstance()
                .getReference("userdata/"+mAuth.getCurrentUser().getUid()+"/favoritos")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        List<Long> idsFavsRemoto = (List<Long>) snapshot.getValue();
                        listaFavoritos.setValue(idsFavsRemoto);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {}
                });
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
                ponerEnListaDesdeJson(result);
                NotificationsViewModel.this.listaFavoritos.setValue(listaFavoritos.getValue());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public LiveData<String> getText() {
        return mText;
    }
    public LiveData<List<Long>> getListaFavoritos() {
        return listaFavoritos;
    }

    public List<HomeViajesRV> getListaViajes(List<Long> nuevosFavs) {
        List<Long> favoritos = listaFavoritos.getValue();
        List<HomeViajesRV> ret = listaViajes.values()
                .stream()
                .map(homeViajesRV -> {
                    if(nuevosFavs.contains(homeViajesRV.getId()))
                        homeViajesRV.setFavorito(true);
                    else
                        homeViajesRV.setFavorito(false);
                    return homeViajesRV;
                })
                .filter(homeViajesRV -> nuevosFavs.contains(homeViajesRV.getId()))
                .collect(Collectors.toList());
        return ret;
    }

    private void ponerEnListaDesdeJson(String json){
        try {
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String id = jsonObject.optString("id");
                Long longId = Long.valueOf(jsonObject.opt("id").toString());
                String title = jsonObject.getJSONObject("title").optString("rendered");
                String informacion = jsonObject.getJSONObject("excerpt").optString("rendered");
                String content = jsonObject.getJSONObject("content").optString("rendered");
                String urlImg = jsonObject.getJSONObject("featured_image").optString("file");
                String precio = jsonObject.optString("price");
                String duracion = jsonObject.getJSONObject("duration").optString("days");
                listaViajes.put(longId,
                        new HomeViajesRV(
                                longId,
                                title,
                                informacion,
                                content,
                                precio,
                                duracion,
                                "https://www.multiversoexplorer.com/wp-content/uploads/" + urlImg));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void pedirJSON() {
        try {
            String url = "https://www.multiversoexplorer.com/wp-json/wp/v2/trip";
            new NotificationsViewModel.HttpAsyncTask().execute(url);
        } catch (Exception e) {}
    }
}