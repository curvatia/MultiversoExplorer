package com.example.multiversoexplorer.ui.ajustes;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.preference.PreferenceFragmentCompat;

import com.example.multiversoexplorer.R;
import com.example.multiversoexplorer.data.model.AuthActivity;
import com.example.multiversoexplorer.databinding.FragmentAjustesBinding;
import com.example.multiversoexplorer.ui.dashboard.WebviewActivity;
import com.example.multiversoexplorer.ui.home.HomeViewModel;
import com.google.firebase.auth.FirebaseAuth;

//public class AjustesFragment extends PreferenceFragmentCompat
//public class AjustesFragment extends Fragment
public class AjustesFragment extends PreferenceFragmentCompat  {

    UserPreference usuarioPref;
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);
        usuarioPref = findPreference("usuarioPref");
        usuarioPref.setIniciarSesionListener(view -> {
            startActivity(new Intent(getContext(), AuthActivity.class));
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //binding = null;
    }


}