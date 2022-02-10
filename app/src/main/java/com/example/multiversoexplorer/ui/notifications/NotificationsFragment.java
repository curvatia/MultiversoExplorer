package com.example.multiversoexplorer.ui.notifications;

import android.content.Intent;
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
import com.example.multiversoexplorer.data.model.AuthActivity;
import com.example.multiversoexplorer.databinding.FragmentNotificationsBinding;
import com.example.multiversoexplorer.ui.home.HomeViajesRV;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;
    private FragmentNotificationsBinding binding;
    private FirebaseAuth mAuth;
    private ReservasAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel = new ViewModelProvider(this).get(NotificationsViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.rvFavoritos.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ReservasAdapter(new ArrayList<>());
        notificationsViewModel.getListaFavoritos().observe(
                getViewLifecycleOwner(), new Observer<List<Long>>() {
                    @Override
                    public void onChanged(List<Long> strings) {
                        adapter.setListaViajes(notificationsViewModel.getListaViajes(strings));
                        binding.rvFavoritos.setAdapter(adapter);
                    }
                }
        );
        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeChanged(int positionStart, int itemCount, @Nullable Object payload) {
                super.onItemRangeChanged(positionStart, itemCount, payload);
                if(payload == null) return;
                HomeViajesRV viaje = (HomeViajesRV) payload;
                notificationsViewModel.publicarFavorito(viaje);
            }
        });

        mAuth = FirebaseAuth.getInstance();
        mAuth.addAuthStateListener(new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser()!=null) {
                    binding.rvFavoritos.setVisibility(View.VISIBLE);
                    binding.panelLoginFavoritos.setVisibility(View.GONE);
                }
            }
        });

        return root;
    }



    @Override
    public void onResume() {
        super.onResume();
        if(FirebaseAuth.getInstance().getCurrentUser()==null){
            binding.panelLoginFavoritos.setVisibility(View.VISIBLE);
            binding.btnLoginFavoritos.setOnClickListener(view -> startActivity(new Intent(getContext(), AuthActivity.class)));
            binding.rvFavoritos.setVisibility(View.GONE);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //binding = null;
    }
}