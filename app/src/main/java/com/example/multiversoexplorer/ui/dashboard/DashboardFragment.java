package com.example.multiversoexplorer.ui.dashboard;

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

import com.example.multiversoexplorer.R;
import com.example.multiversoexplorer.data.model.AuthActivity;
import com.example.multiversoexplorer.databinding.FragmentDashboardBinding;
import com.example.multiversoexplorer.ui.login.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    private FragmentDashboardBinding binding;
    private FirebaseAuth mAuth;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel = new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        checkLogin();
        return binding.getRoot();
    }

    private void checkLogin() {
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            binding.panelLogin.setVisibility(View.GONE);
            binding.panelViajesNuevos.setVisibility(View.VISIBLE);
        } else {
            binding.button.setOnClickListener(view -> startActivity(new Intent(getContext(), AuthActivity.class)));
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        checkLogin();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}