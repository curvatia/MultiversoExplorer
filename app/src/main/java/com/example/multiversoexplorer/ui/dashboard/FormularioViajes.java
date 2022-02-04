
package com.example.multiversoexplorer.ui.dashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.multiversoexplorer.R;


public class FormularioViajes extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private TextView etFechaIda;
    private TextView etFechaVuelta;
    private boolean ida = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_viajes);
        etFechaIda = findViewById(R.id.etFechaIda);
        etFechaVuelta = findViewById(R.id.etFechaVuelta);
        etFechaIda.setOnClickListener(view -> {
            ida = true;
            showDatePicker();
        });
        etFechaVuelta.setOnClickListener(view -> {
            ida = false;
            showDatePicker();
        });
        setTitle("Crear Viaje");
    }

    private void showDatePicker() {
        Calendar now = Calendar.getInstance();
        DatePickerDialog calendarioFecha = new DatePickerDialog(
                this,
                this,
                now.get(Calendar.DAY_OF_MONTH),
                now.get(Calendar.MONTH),
                now.get(Calendar.YEAR));
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