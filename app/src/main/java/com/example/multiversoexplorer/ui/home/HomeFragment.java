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

import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    private RecyclerView miReciclador;

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
                publicarFavorito(viaje);
            }
        };

        homeViewModel.getListaViajes().observe(getViewLifecycleOwner(), new Observer<List<HomeViajesRV>>() {
            @Override
            public void onChanged(List<HomeViajesRV> homeViajesRVS) {
                ReservasAdapter adapter = new ReservasAdapter(homeViajesRVS);
                miReciclador.setAdapter(adapter);
                adapter.registerAdapterDataObserver(adapterDataObserver);
            }
        });

        return root;
    }

    private void publicarFavorito(HomeViajesRV viaje) {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        if(mAuth.getCurrentUser()!=null){
            DatabaseReference reference = FirebaseDatabase.getInstance()
                    .getReference("userdata/"+mAuth.getCurrentUser().getUid()+"/favoritos");
            reference.addValueEventListener(new ValueEventListener() {
                private ValueEventListener listener = this;
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    List<Long> datos = (List<Long>) snapshot.getValue();
                    if(viaje.isEsFavorito()){
                        if(!datos.contains(viaje.getId()))
                            datos.add(viaje.getId());
                    } else {
                        if(datos.contains(viaje.getId()))
                            datos.remove(datos.indexOf(viaje.getId()));
                    }
                    //reference.removeEventListener(listener);
                    reference.setValue(datos);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {}
            });
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //binding = null;
    }
}