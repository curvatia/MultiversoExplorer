package com.example.multiversoexplorer.ui.home;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.multiversoexplorer.R;
import com.squareup.picasso.Picasso;

public class HomeViajesActivity extends Activity {

    private ImageView Pictures;
    private HomeViajesRV Viajes;
    private TextView Nombre, Info;
    private Button btnReservar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

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


        btnReservar = findViewById(R.id.btnReservar);
        btnReservar.setOnClickListener(view -> {
            startActivity(new Intent(this, HomeWebViewActivity.class));
        });
        

    }
}