package com.example.multiversoexplorer.ui.home;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
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

        Picasso.get().load(Viajes.getFotos()).into(Pictures);
        //this.Pictures.setImageResource(Viajes);//this.Viajes.getFotos()
        this.Nombre.setText(this.Viajes.getViaje());
        Spanned spanned = Html.fromHtml(this.Viajes.getContent());
        this.Info.setText(spanned);
    }
}