package com.example.multiversoexplorer.ui.home;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.multiversoexplorer.R;
import com.squareup.picasso.Picasso;

public class HomeViajesActivity extends AppCompatActivity {

    private ImageView Pictures;
    private HomeViajesRV Viajes;
    private TextView Nombre, Info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_viajes);

        //NEW
        Bundle bundle = getIntent().getExtras();
        this.Viajes = (HomeViajesRV)
                bundle.getSerializable("reservas");

        this.Pictures = findViewById(R.id.imgPic);
        this.Nombre = findViewById(R.id.tvTitulo);
        this.Info = findViewById(R.id.tvDescripcion);

        //Picasso.with(context).load(mThumbIds[position]).centerCrop().into(imageView);
        //this.Pictures.setImageResource(mTh);//this.Viajes.getFotos()
        this.Nombre.setText(this.Viajes.getViaje());
        this.Info.setText(this.Viajes.getInformacion());
    }
}