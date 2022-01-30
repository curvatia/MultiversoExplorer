package com.example.multiversoexplorer.model.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.multiversoexplorer.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    //2ºREGISTROFB defining view objects
    private EditText TextEmail;
    private EditText TextPassword;
    private Button btnRegistrar;
    private ProgressDialog progressDialog;
    //Declaramos un objeto firebaseAuth
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //2ºREGISTROFB
        //inicializamos el objeto firebaseAuth
        firebaseAuth = FirebaseAuth.getInstance();
        //Referenciamos los views
        TextEmail = (EditText) findViewById(R.id.TxtEmail);
        TextPassword = (EditText) findViewById(R.id.TxtPassword);
        btnRegistrar = (Button) findViewById(R.id.botonRegistrar);
        progressDialog = new ProgressDialog(this);
        //attaching listener to button
        btnRegistrar.setOnClickListener(this);
        //END2º

    }

    //2º MÉTODOS REGISTROFB  ||alice@ya.es 123456
    private void registrarUsuario(){

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

        //creating a new user --> alice@ya.es getafe12345 || bob@ya.es 123456 || erik@ya.es 123456 || charlie@ya.es 123456 // alice@ya.com getafe12345
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //checking if success
                        if(task.isSuccessful()){

                            Toast.makeText(LoginActivity.this,"Se ha registrado el usuario con el email: "+ TextEmail.getText(),Toast.LENGTH_LONG).show();
                        }else{

                            Toast.makeText(LoginActivity.this,"Ya existe el usuario con el mismo mail",Toast.LENGTH_LONG).show();
                        }
                        progressDialog.dismiss();
                    }
                });

    }

    @Override
    public void onClick(View view) {
        //Invocamos al método:
        registrarUsuario();
    }
    //2º FIN MÉTODOS REGISTROFB
}