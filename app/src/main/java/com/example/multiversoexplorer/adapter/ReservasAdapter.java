package com.example.multiversoexplorer.adapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.multiversoexplorer.R;
import com.example.multiversoexplorer.ui.home.HomeViajesActivity;
import com.example.multiversoexplorer.ui.home.HomeViajesRV;
import com.squareup.picasso.Picasso;

import java.util.List;

//AL FINAL?! implements View.OnClickListener
public class ReservasAdapter extends RecyclerView.Adapter<ReservasAdapter.ReservasViewHolder> {

    private final List<HomeViajesRV> listaViajes;
    private int posicionseleccionada = -1;

    //¿?
    //private View.OnClickListener listener;

    public ReservasAdapter(List<HomeViajesRV>Listado) {this.listaViajes = Listado;}

    //MÉTODO 1
    @Override
    public ReservasViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_home, parent, false));

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_home, parent,false);
       //View.setOnClickListener(this);//¿?
        return new ReservasViewHolder(view);
    }

    //MÉTODO 2
    @Override
    public void onBindViewHolder(ReservasViewHolder holder, @SuppressLint("RecyclerView") int position) {

        String picTrip = listaViajes.get(position).getFotos();
        Picasso.get().load(picTrip).into(holder.FotoViaje);
        //holder.FotoViaje.setImageResource(listaViajes.get(position).getFotos());

        holder.Titulo.setText(listaViajes.get(position).getViaje());
        Spanned spanned = Html.fromHtml(listaViajes.get(position).getInformacion());
        holder.Descripcion.setText(spanned);

        holder.CardViewHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                posicionseleccionada = position;
                notifyDataSetChanged();
            }
        });
        if (posicionseleccionada == position) {
            HomeViajesRV homeViajesRV = listaViajes.get(posicionseleccionada);
            Intent i = new Intent(holder.itemView.getContext(), HomeViajesActivity.class);
            i.putExtra("reservas",homeViajesRV);
            holder.itemView.getContext().startActivity(i);
        }
    }

    //MÉTODO 3
    @Override
    public int getItemCount() {return listaViajes.size();}

    //¿?
    /*public void setOnClickListener(View.OnClickListener listener) {
        this.listener=listener;
    }
    //¿?
    @Override
    public void onClick(View view) {
        if (listener != null) {
            listener.onClick(view);
        }
    }*/

    //MÉTODO 4
    public static class ReservasViewHolder extends RecyclerView.ViewHolder {

        private final ImageView FotoViaje;
        private final TextView Titulo, Descripcion;
        private final CardView CardViewHome;

        public ReservasViewHolder(View view) {
            super(view);

            this.FotoViaje = (ImageView) view.findViewById(R.id.idImg);

            this.Titulo = (TextView) view.findViewById(R.id.tvTitulo);
            this.Descripcion = (TextView) view.findViewById(R.id.tvDescripcion);

            this.CardViewHome = (CardView) view.findViewById(R.id.EtiCardView);
        }
        //RIGHT?!
        public ImageView getFotoViaje() {return FotoViaje;}
        public TextView getTitulo() {return Titulo;}
        public CardView getCardViewHome() {return CardViewHome;}

    }


}
