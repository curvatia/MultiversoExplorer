package com.example.multiversoexplorer.data.model;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.multiversoexplorer.MainActivity;
import com.example.multiversoexplorer.R;
import com.example.multiversoexplorer.ui.login.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AuthActivity extends AppCompatActivity {

    private Button btnIniciarSesion;
    private Button btnRegistro;
    private FirebaseAuth mAuth;
// ...
// Initialize Firebase Auth

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        btnIniciarSesion = findViewById(R.id.btnIniciarSesion);
        btnRegistro = findViewById(R.id.btnRegistro);
        btnIniciarSesion.setOnClickListener(view -> startActivity(new Intent(AuthActivity.this.getBaseContext(), LoginActivity.class)));
        btnRegistro.setOnClickListener(view -> startActivity(new Intent(AuthActivity.this.getBaseContext(), RegistroActivity.class)));
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(mAuth.getCurrentUser()!=null){
            finish();
        }
    }
}