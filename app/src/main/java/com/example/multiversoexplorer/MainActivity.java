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

    //NEW CERO
    private TextView tvHola;

    //1º
    private Button btnEntrar;

    //2ºREGISTROFB defining view objects
    private EditText TextEmail;
    private EditText TextPassword;
    private Button btnRegistrar;
    private ProgressDialog progressDialog;
    //Declaramos un objeto firebaseAuth
    private FirebaseAuth firebaseAuth;

    //3ºRECYCLERVIEW-CARDVIEW
    private RecyclerView miReciclador;
    private ReservasAdapter miAdaptador;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//0ºSPLASH
/*        TimerTask tarea = new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, SplashActivity.class);
                startActivity(intent);
                finish();
            }

        Timer tiempo = new Timer();
        tiempo.schedule(tarea,10000);//TIEMPO DE ESPERA 5SEG
        };*/
        //FIN 0ºSPLASH
        //1º
        /*btnEntrar = (Button) findViewById(R.id.btnBoton);
        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, BottomNavActivity.class);
                startActivity(i);
            }
        });*/
        //END1º

        Intent intent = new Intent(this, BottomNavActivity.class);
        startActivity(intent);

        //2ºREGISTROFB
        /*//inicializamos el objeto firebaseAuth
        firebaseAuth = FirebaseAuth.getInstance();
        //Referenciamos los views
        TextEmail = (EditText) findViewById(R.id.TxtEmail);
        TextPassword = (EditText) findViewById(R.id.TxtPassword);
        btnRegistrar = (Button) findViewById(R.id.botonRegistrar);
        progressDialog = new ProgressDialog(this);
        //attaching listener to button
        btnRegistrar.setOnClickListener(this);*/
        //END2ºREGISTROFB


    }//END onCreate


    //2º MÉTODOS REGISTROFB  ||alice@ya.es 123456
    /*private void registrarUsuario(){

        //Obtenemos el email y la contraseña desde las cajas de texto
        String email = TextEmail.getText().toString().trim();
        String password  = TextPassword.getText().toString().trim();

        //Verificamos que las cajas de texto no estén vacías
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Se debe ingresar un email",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Falta ingresar la contraseña",Toast.LENGTH_LONG).show();
            return;
        }

        progressDialog.setMessage("Realizando registro en linea...");
        progressDialog.show();

        //creating a new user --> alice@ya.es 123456
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //checking if success
                        if(task.isSuccessful()){

                            Toast.makeText(MainActivity.this,"Se ha registrado el usuario con el email: "+ TextEmail.getText(),Toast.LENGTH_LONG).show();
                        }else{

                            Toast.makeText(MainActivity.this,"Ya existe el usuario con el mismo mail",Toast.LENGTH_LONG).show();
                        }
                        progressDialog.dismiss();
                    }
                });

    }

    @Override
    public void onClick(View view) {
        //Invocamos al método:
        registrarUsuario();
    }*/
    //2º FIN MÉTODOS REGISTROFB





}//END MainActivity