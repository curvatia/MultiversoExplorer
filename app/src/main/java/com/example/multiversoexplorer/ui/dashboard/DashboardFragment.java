package com.example.multiversoexplorer.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.multiversoexplorer.adapter.TicketsAdapter;
import com.example.multiversoexplorer.data.model.AuthActivity;
import com.example.multiversoexplorer.databinding.FragmentDashboardBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    private FragmentDashboardBinding binding;
    private FirebaseAuth mAuth;
    DatabaseReference miDatabaseReference=
            FirebaseDatabase.getInstance().getReference();

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel = new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);

        dashboardViewModel.getListaViajesCreados().observe(
                DashboardFragment.this, new Observer<List<DashboardViajesRV>>() {
                    @Override
                    public void onChanged(List<DashboardViajesRV> dashboardViajesRVS) {
                        binding.rvDashboard.setAdapter(new TicketsAdapter(dashboardViajesRVS));
                    }
                });

        mAuth = FirebaseAuth.getInstance();
        mAuth.addAuthStateListener(new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser()!=null) {
                    binding.panelLogin.setVisibility(View.GONE);
                    binding.panelViajesNuevos.setVisibility(View.VISIBLE);
                } else {
                    binding.button.setOnClickListener(view -> startActivity(new Intent(getContext(), AuthActivity.class)));
                }
            }
        });

        binding.floatingActionButton.setOnClickListener(view -> {
            startActivity(new Intent(getContext(), FormularioViajes.class));
        });

        return binding.getRoot();
    }

    private void observarViajes() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}