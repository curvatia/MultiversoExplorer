package com.example.multiversoexplorer.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    private RecyclerView miReciclador;
    private ReservasAdapter miAdaptador;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //3ºRECYCLERVIEW FUNCIONA EN Pixel 3 API 30
        this.miReciclador = binding.RecicladorView;
        this.miReciclador.setHasFixedSize(true);
        miReciclador.setLayoutManager(new LinearLayoutManager(getContext()));
        this.miAdaptador = new ReservasAdapter(DatosViajes());
        this.miReciclador.setAdapter(miAdaptador);
        //END3ºRECYCLERVIEW
        return root;
    }
    //3ºRECYCLERVIEW
    public List<HomeViajesRV> DatosViajes() {

        List<HomeViajesRV> lista = new ArrayList<>();

        lista.add(new HomeViajesRV("Viajes de Aventura", "Dentro de un mismo destino", "https://www.multiversoexplorer.com/wp-content/uploads/2018/04/P1100903.jpg"));
        lista.add(new HomeViajesRV("Viajes de Aventura", "Dentro de un mismo destino", "https://www.multiversoexplorer.com/wp-content/uploads/2018/04/P1110164.jpg"));
        lista.add(new HomeViajesRV("Viajes de Aventura", "Dentro de un mismo destino", "https://www.multiversoexplorer.com/wp-content/uploads/2018/04/P1100949.jpg"));
        lista.add(new HomeViajesRV("Viajes de Aventura", "Dentro de un mismo destino", "https://www.multiversoexplorer.com/wp-content/uploads/2018/04/P1110056.jpg"));
        lista.add(new HomeViajesRV("Viajes de Aventura", "Dentro de un mismo destino", "https://www.multiversoexplorer.com/wp-content/uploads/2018/04/P1110227.jpg"));
        lista.add(new HomeViajesRV("Viajes de Aventura", "Dentro de un mismo destino", "https://www.multiversoexplorer.com/wp-content/uploads/2018/04/P1110143.jpg"));
        lista.add(new HomeViajesRV("Viajes de Aventura", "Dentro de un mismo destino", "https://www.multiversoexplorer.com/wp-content/uploads/2018/04/P1110080.jpg"));
        lista.add(new HomeViajesRV("Viajes de Aventura", "Dentro de un mismo destino", "https://www.multiversoexplorer.com/wp-content/uploads/2018/04/P1100826.jpg"));
        lista.add(new HomeViajesRV("Viajes de Aventura", "Dentro de un mismo destino", "https://www.multiversoexplorer.com/wp-content/uploads/2018/04/P1100911.jpg"));
        lista.add(new HomeViajesRV("Viajes de Aventura", "Dentro de un mismo destino", "https://www.multiversoexplorer.com/wp-content/uploads/2018/04/P1100824.jpg"));
        return lista;
    }
    //END 3ºRECYCLERVIEW

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}