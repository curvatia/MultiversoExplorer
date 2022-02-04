package com.example.multiversoexplorer.ui.home;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.multiversoexplorer.R;
import com.example.multiversoexplorer.adapter.ReservasAdapter;
import com.example.multiversoexplorer.databinding.FragmentHomeBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    private RecyclerView miReciclador;
    private ReservasAdapter miAdaptador;
    private JSONObject jsonObject;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //3ºRECYCLERVIEW FUNCIONA EN Pixel 3 API 30
        this.miReciclador = binding.RecicladorView;
        this.miReciclador.setHasFixedSize(true);
        miReciclador.setLayoutManager(new LinearLayoutManager(getContext()));
        pedirJSON();
        //END3ºRECYCLERVIEW
        return root;
    }
    //3ºRECYCLERVIEW
    public void pedirJSON() {
        try {
            String url = "https://www.multiversoexplorer.com/wp-json/wp/v2/trip";
            new HttpAsyncTask().execute(url);
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
                if(result == ""){
                    Toast.makeText(getContext(), "Respuesta Vacia", Toast.LENGTH_LONG).show();
                    return;
                }

                ArrayList<HomeViajesRV> lista = new ArrayList<>();
                JSONArray jsonArray = new JSONArray(result);
                for (int i = 0; i < jsonArray.length(); i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    try {
                        String title = jsonObject.getJSONObject("title").optString("rendered");
                        String informacion = jsonObject.getJSONObject("excerpt").optString("rendered");
                        String content = jsonObject.getJSONObject("content").optString("rendered");
                        String urlImg = jsonObject.getJSONObject("featured_image").optString("file");
                        String precio = jsonObject.optString("price");
                        String duracion = jsonObject.getJSONObject("duration").optString("days");
                        lista.add(
                                new HomeViajesRV(
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

                miAdaptador = new ReservasAdapter(lista);
                miReciclador.setAdapter(miAdaptador);
            } catch (Exception e) {
                Toast.makeText(getContext(), "Respuesta Invalida", Toast.LENGTH_LONG);
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}