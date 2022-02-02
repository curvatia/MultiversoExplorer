package com.example.multiversoexplorer.data.model;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.multiversoexplorer.R;
import com.example.multiversoexplorer.ui.login.LoginActivity;

public class AuthActivity extends AppCompatActivity {

    private Button btnIniciarSesion;
    private Button btnRegistro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        btnIniciarSesion = findViewById(R.id.btnIniciarSesion);
        btnRegistro = findViewById(R.id.btnRegistro);
        btnIniciarSesion.setOnClickListener(view -> startActivity(new Intent(AuthActivity.this.getBaseContext(), LoginActivity.class)));
        btnRegistro.setOnClickListener(view -> startActivity(new Intent(AuthActivity.this.getBaseContext(), RegistroActivity.class)));
    }
}