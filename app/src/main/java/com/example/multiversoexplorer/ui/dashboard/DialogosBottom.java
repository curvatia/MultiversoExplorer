package com.example.multiversoexplorer.ui.dashboard;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import com.example.multiversoexplorer.R;

public class DialogosBottom extends BottomSheetDialogFragment {

    EditText txtResultado;

    public dialogosBottomListener getmListener() {
        return mListener;
    }

    public void setmListener(dialogosBottomListener mListener) {
        this.mListener = mListener;
    }

    private dialogosBottomListener mListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialogosbottom, container, false);

        //LECTURA DEL QR
        //txtResultado = v.findViewById(R.id.txtResultado);

        ImageButton imgVuelos = v.findViewById(R.id.imgVuelos);
        ImageButton imgCoches = v.findViewById(R.id.imgCoches);

        imgVuelos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onButtonClicked("Vuelos clickado");

                IntentIntegrator integrador = new IntentIntegrator(getActivity());
                integrador.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);

                integrador.setCameraId(0);
                integrador.setBeepEnabled(true);
                integrador.setBarcodeImageEnabled(true);
                integrador.initiateScan();

                dismiss();

            }
        });
        imgCoches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onButtonClicked("Coches clickado");
                dismiss();
            }
        });

        return v;
    }

    public interface dialogosBottomListener {
        public void onButtonClicked(String text);
    }


}

