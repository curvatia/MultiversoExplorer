package com.example.multiversoexplorer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

public class MainActivity extends AppCompatActivity {

    //NEW CERO
    //private TextView tvHola;

    //1º
    //private Button btnEntrar;

    //2ºREGISTROFB defining view objects
    /*private EditText TextEmail;
    private EditText TextPassword;
    private Button btnRegistrar;
    private ProgressDialog progressDialog;
    //Declaramos un objeto firebaseAuth
    private FirebaseAuth firebaseAuth;*/

    //3ºRECYCLERVIEW-CARDVIEW
    private RecyclerView miReciclador;
    private ReservasAdapter miAdaptador;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //3ºRECYCLERVIEW FUNCIONA EN Pixel 3 API 30
        this.miReciclador = (RecyclerView) findViewById(R.id.RecicladorView);
        this.miReciclador.setHasFixedSize(true);
        miReciclador.setLayoutManager(new LinearLayoutManager(this));
        this.miAdaptador = new ReservasAdapter(DatosViajes());
        this.miReciclador.setAdapter(miAdaptador);
        //END3ºRECYCLERVIEW

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


    //3ºRECYCLERVIEW
    public List<HomeViajesRV> DatosViajes() {

        List<HomeViajesRV> lista = new ArrayList<>();

        lista.add(new HomeViajesRV("Viajes de Aventura", "Dentro de un mismo destino", "https://www.multiversoexplorer.com/wp-content/uploads/2018/04/P1100903.jpg"));
        lista.add(new HomeViajesRV("Viajes de Aventura", "Dentro de un mismo destino", "https://www.multiversoexplorer.com/wp-content/uploads/2018/04/P1110164.jpg"));
        lista.add(new HomeViajesRV("Viajes de Aventura", "Dentro de un mismo destino", "https://www.multiversoexplorer.com/wp-content/uploads/2018/04/P1100949.jpg"));
        lista.add(new HomeViajesRV("Viajes de Aventura", "Dentro de un mismo destino", "https://www.multiversoexplorer.com/wp-content/uploads/2018/04/P1110056.jpg"));
        lista.add(new HomeViajesRV("Viajes de Aventura", "Dentro de un mismo destino", "https://www.multiversoexplorer.com/wp-content/uploads/2018/04/P1110227.jpg"));
        lista.add(new HomeViajesRV("Viajes de Aventura", "Dentro de un mismo destino", "https://www.multiversoexplorer.com/wp-content/uploads/2018/04/P1110143.jpg"));
        lista.add(new HomeViajesRV("Viajes de Aventura", "Dentro de un mismo destino", "https://www.multiversoexplorer.com/wp-content/uploads/2018/04/P1110080.jpg"));
        lista.add(new HomeViajesRV("Viajes de Aventura", "Dentro de un mismo destino", "https://www.multiversoexplorer.com/wp-content/uploads/2018/04/P1100826.jpg"));
        lista.add(new HomeViajesRV("Viajes de Aventura", "Dentro de un mismo destino", "https://www.multiversoexplorer.com/wp-content/uploads/2018/04/P1100911.jpg"));
        lista.add(new HomeViajesRV("Viajes de Aventura", "Dentro de un mismo destino", "https://www.multiversoexplorer.com/wp-content/uploads/2018/04/P1100824.jpg"));
        return lista;
    }
    //END 3ºRECYCLERVIEW


}//END MainActivity