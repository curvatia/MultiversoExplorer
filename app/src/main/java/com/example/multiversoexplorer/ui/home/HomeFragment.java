package com.example.multiversoexplorer.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.AdapterDataObserver;

import com.example.multiversoexplorer.adapter.ReservasAdapter;
import com.example.multiversoexplorer.databinding.FragmentHomeBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    private RecyclerView miReciclador;
    private ReservasAdapter adapter = new ReservasAdapter(new ArrayList<>());

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        this.miReciclador = binding.RecicladorView;
        this.miReciclador.setHasFixedSize(true);

        miReciclador.setLayoutManager(new LinearLayoutManager(getContext()));
        AdapterDataObserver adapterDataObserver = new AdapterDataObserver() {
            @Override
            public void onItemRangeChanged(int positionStart, int itemCount, Object payload) {
                super.onItemRangeChanged(positionStart, itemCount);
                if(payload == null) return;
                HomeViajesRV viaje = (HomeViajesRV) payload;
                homeViewModel.publicarFavorito(viaje);
            }
        };
        adapter.registerAdapterDataObserver(adapterDataObserver);
        miReciclador.setAdapter(adapter);
        homeViewModel.getListaFavoritos().observe(getViewLifecycleOwner(), new Observer<List<Long>>() {
            @Override
            public void onChanged(List<Long> longs) {
                adapter.setListaViajes(
                        homeViewModel.getListaViajesNuevos(
                                homeViewModel.getListaViajes().getValue(),
                                longs));
            }
        });
        homeViewModel.getListaViajes().observe(getViewLifecycleOwner(), new Observer<List<HomeViajesRV>>() {
            @Override
            public void onChanged(List<HomeViajesRV> homeViajesRVS) {
                adapter.setListaViajes(homeViewModel.getListaViajes().getValue());
            }
        });
/*        homeViewModel.getListaFavoritos().observe(getViewLifecycleOwner(), new Observer<List<Long>>() {
            @Override
            public void onChanged(List<Long> longs) {
                ReservasAdapter adapter = new ReservasAdapter(
                        homeViewModel.getListaViajesNuevos(longs)
                );
                miReciclador.setAdapter(adapter);
                adapter.registerAdapterDataObserver(adapterDataObserver);
            }
        });*/

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //binding = null;
    }
}