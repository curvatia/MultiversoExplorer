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
    private MutableLiveData<List<String>> listaFavoritos;
    private MutableLiveData<ConcurrentHashMap<String, HomeViajesRV>> listaViajes;
    private List<String> pendientes = new ArrayList<>();
    private String json;

    private FirebaseAuth mAuth;
    DatabaseReference miDatabaseReference=
            FirebaseDatabase.getInstance().getReference();

    public NotificationsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("FRAGMENT DE VIAJES FAVORITOS DESDE HOME");
        listaFavoritos = new MutableLiveData<List<String>>(new ArrayList<>());
        listaViajes = new MutableLiveData<ConcurrentHashMap<String, HomeViajesRV>>(new ConcurrentHashMap<>());

        mAuth = FirebaseAuth.getInstance();
        mAuth.addAuthStateListener(new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser()!=null) {
                    observarIdsFavoritos();
                }
            }
        });
    }

    private void observarIdsFavoritos(){
        FirebaseDatabase.getInstance()
                .getReference("userdata/"+mAuth.getCurrentUser().getUid()+"/favoritos")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        List<Long> datos = (List<Long>) snapshot.getValue();
                        for(Long k : datos){
                            if(!listaViajes.getValue().containsKey(k)) {
                                añadirDesdeJson(String.valueOf(k));
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {}
                })
                ;
    }

    private void añadirDesdeJson(String k){
        this.pendientes.add(k);
        if(this.json == null){
            pedirJSON();
        } else {
            procesarPendientes();
        }
    }

    private Object modificarViajes = new Object();
    private void procesarPendientes(){
        for (String k : this.pendientes) {
            buscarEnJson(k);
        }
        this.pendientes.clear();
        listaViajes.setValue(listaViajes.getValue());
    }

    private void buscarEnJson(String k){
        try {
            JSONArray jsonArray = new JSONArray(this.json);
            for (int i = 0; i < jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String id = jsonObject.optString("id");
                    if(!id.equals(k)) continue;
                    String title = jsonObject.getJSONObject("title").optString("rendered");
                    String informacion = jsonObject.getJSONObject("excerpt").optString("rendered");
                    String content = jsonObject.getJSONObject("content").optString("rendered");
                    String urlImg = jsonObject.getJSONObject("featured_image").optString("file");
                    String precio = jsonObject.optString("price");
                    String duracion = jsonObject.getJSONObject("duration").optString("days");
                    listaViajes.getValue().put(k,
                            new HomeViajesRV(
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

    //3ºRECYCLERVIEW
    public void pedirJSON() {
        try {
            String url = "https://www.multiversoexplorer.com/wp-json/wp/v2/trip";
            new NotificationsViewModel.HttpAsyncTask().execute(url);
        } catch (Exception e) {}
        List<HomeViajesRV> lista = new ArrayList<>();
    }
    //END 3ºRECYCLERVIEW
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
                NotificationsViewModel.this.json = result;
                procesarPendientes();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public LiveData<String> getText() {
        return mText;
    }
    public LiveData<List<String>> getListaFavoritos() {
        return listaFavoritos;
    }
    public MutableLiveData<ConcurrentHashMap<String, HomeViajesRV>> getListaViajes() {
        return listaViajes;
    }
}