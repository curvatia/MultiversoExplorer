
package com.example.multiversoexplorer.ui.dashboard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.multiversoexplorer.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;
import java.util.HashMap;


public class FormularioViajes extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private EditText etDestino;
    private EditText etFechaIda;
    private EditText etFechaVuelta;
    private boolean ida = false;
    private View btnGuardarViajes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_viajes);
        setTitle("Crear Viaje");
        etDestino = findViewById(R.id.etDestino);
        etFechaIda = findViewById(R.id.etFechaIda);
        etFechaVuelta = findViewById(R.id.etFechaVuelta);
        btnGuardarViajes = findViewById(R.id.btnGuardarViajes);
        etFechaIda.setOnClickListener(view -> {
            ida = true;
            showDatePicker();
        });
        etFechaVuelta.setOnClickListener(view -> {
            ida = false;
            showDatePicker();
        });

        btnGuardarViajes.setOnClickListener(view -> {
            guardarDatos();
        });
    }

    private void guardarDatos(){
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        if(mAuth.getCurrentUser()==null) return;
        DatabaseReference reference = FirebaseDatabase.getInstance()
                .getReference("userdata/" + mAuth.getCurrentUser().getUid() + "/viajesCreados");
        reference.addValueEventListener(new ValueEventListener() {
            private String Destino = etDestino.getText().toString();
            private String Ida = etFechaIda.getText().toString();
            private String Vuelta = etFechaVuelta.getText().toString();
            private ValueEventListener listener = this;
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<HashMap<String, String>> viajes = (List<HashMap<String, String>>)snapshot.getValue();
                viajes.add(new HashMap<String, String>(){{
                    put("destino",Destino);
                    put("fechaIda",Ida);
                    put("fechaVuelta",Vuelta);
                    put("img","https://www.multiversoexplorer.com/wp-content/uploads/2021/05/Trekking_Manaslu_Multiverso_Explorer.jpg");
                }});
                reference.removeEventListener(listener);
                reference.setValue(viajes);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });

    }

    private void showDatePicker() {
        Calendar now = Calendar.getInstance();
        DatePickerDialog calendarioFecha = new DatePickerDialog(
                this,
                this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
                );
        calendarioFecha.show();
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        if(ida){
            etFechaIda.setText(i+"/"+i1+"/"+i2);
        } else {
            etFechaVuelta.setText(i+"/"+i1+"/"+i2);
        }
    }
}