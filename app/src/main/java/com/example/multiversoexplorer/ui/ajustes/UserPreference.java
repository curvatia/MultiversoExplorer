package com.example.multiversoexplorer.ui.ajustes;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.preference.Preference;
import androidx.preference.PreferenceViewHolder;

import com.example.multiversoexplorer.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserPreference extends Preference {
    private ImageView imageView;
    View.OnClickListener iniciarSesionListener;
    private Button btnInicSesionPref;
    private Button btnCerrSesionPref;
    private TextView tvNombreUsuarioPref;

    public UserPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onBindViewHolder(PreferenceViewHolder holder) {
        super.onBindViewHolder(holder);
        imageView = (ImageView)holder.findViewById(R.id.image);
        btnInicSesionPref = (Button) holder.findViewById(R.id.btnInicSesionPref);
        btnCerrSesionPref = (Button) holder.findViewById(R.id.btnCerrSesionPref);
        tvNombreUsuarioPref = (TextView) holder.findViewById(R.id.tvNombreUsuarioPref);
        btnCerrSesionPref.setOnClickListener(view -> {
            FirebaseAuth.getInstance().signOut();
        });
        FirebaseAuth.getInstance().addAuthStateListener(firebaseAuth -> {
            if(firebaseAuth.getCurrentUser()==null){
                btnCerrSesionPref.setVisibility(View.GONE);
                btnInicSesionPref.setVisibility(View.VISIBLE);
                tvNombreUsuarioPref.setText("usuario invitado");
            } else {
                btnCerrSesionPref.setVisibility(View.VISIBLE);
                btnInicSesionPref.setVisibility(View.GONE);
                tvNombreUsuarioPref.setText(firebaseAuth.getCurrentUser().getEmail());
            }
        });
        btnInicSesionPref.setOnClickListener(iniciarSesionListener);
        if(FirebaseAuth.getInstance().getCurrentUser()!=null){
            FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
            String username = currentUser.getEmail();
            tvNombreUsuarioPref.invalidate();
            tvNombreUsuarioPref.setText(username);
        }
    }

    public void setIniciarSesionListener(View.OnClickListener iniciarSesionListener) {
        this.iniciarSesionListener = iniciarSesionListener;
    }
}
