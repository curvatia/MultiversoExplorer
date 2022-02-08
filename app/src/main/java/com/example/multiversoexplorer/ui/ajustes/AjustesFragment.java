package com.example.multiversoexplorer.ui.ajustes;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.preference.PreferenceFragmentCompat;

import com.example.multiversoexplorer.R;
import com.example.multiversoexplorer.databinding.FragmentAjustesBinding;
import com.example.multiversoexplorer.ui.dashboard.WebviewActivity;
import com.example.multiversoexplorer.ui.home.HomeViewModel;
import com.google.firebase.auth.FirebaseAuth;

//public class AjustesFragment extends PreferenceFragmentCompat
//public class AjustesFragment extends Fragment
public class AjustesFragment extends Fragment  {

    private AjustesViewModel ajustesViewModel;
    private FragmentAjustesBinding binding;

    private View privacidad;


    //NEW FROM SETTINGS
    /*@Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);
    }*/

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        /*privacidad = (R.id.tvPrivacidad);
        privacidad.setOnClickListener(view -> {
            startActivity(new Intent(this, AjusteswebViewActivity.class));
        });*/

        ajustesViewModel = new ViewModelProvider(this).get(AjustesViewModel.class);

        binding = FragmentAjustesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.btnSingOut.setOnClickListener(view -> {
            FirebaseAuth mAuth = FirebaseAuth.getInstance();
            mAuth.signOut();
        });

        final TextView textView = binding.textAjustes;
        ajustesViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) { textView.setText(s); }
        });
        return  root;

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //binding = null;
    }


}