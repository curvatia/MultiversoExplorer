package com.example.multiversoexplorer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.multiversoexplorer.adapter.ReservasAdapter;
import com.example.multiversoexplorer.ui.home.HomeViajesRV;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TimerTask tarea = new TimerTask() {
            @Override
            public void run() {
                synchronized (this){
                    try {
                        wait(1000);
                        Intent intent = new Intent(MainActivity.this, SplashActivity.class);
                        startActivity(intent);
                        finish();
                        Intent bottomNav = new Intent(MainActivity.this, BottomNavActivity.class);
                        startActivity(bottomNav);
                    } catch (InterruptedException e) {}
                }
            }
        };
        Timer tiempo = new Timer();
        tiempo.schedule(tarea,0);//TIEMPO DE ESPERA 5SEG

    }//END onCreate

}//END MainActivity