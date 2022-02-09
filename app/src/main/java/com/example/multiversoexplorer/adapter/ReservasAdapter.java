package com.example.multiversoexplorer.adapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.text.Html;
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

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ReservasAdapter extends RecyclerView.Adapter<ReservasAdapter.ReservasViewHolder> {

    private List<HomeViajesRV> listaViajes;
    public ReservasAdapter(List<HomeViajesRV> Listado) {
        this.listaViajes = Listado;
    }

    @Override
    public ReservasViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_home, parent,false);
        return new ReservasViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ReservasViewHolder holder, @SuppressLint("RecyclerView") int position) {
        HomeViajesRV viaje = listaViajes.get(position);
        Picasso.get().load(viaje.getFotos()).into(holder.FotoViaje);
        holder.Titulo.setText(viaje.getViaje());
        holder.Descripcion.setText(Html.fromHtml(viaje.getInformacion()));
        holder.Precio.setText(viaje.getPrecio() + " €");
        holder.Dias.setText(viaje.getDias() + " días");
        holder.Fblike.setOnClickListener(view -> {
            viaje.setFavorito(!viaje.isEsFavorito());
            notifyItemChanged(position, viaje);
        });
        holder.CardViewHome.setOnClickListener(view -> {
            holder.itemView.getContext().startActivity(
                    new Intent(holder.itemView.getContext(), HomeViajesActivity.class)
                            .putExtra("reservas",viaje)
            );
        });
        if(viaje.isEsFavorito())
            holder.Fblike.setColorFilter(Color.RED);
    }

    @Override
    public int getItemCount() {return listaViajes.size();}

    public static class ReservasViewHolder extends RecyclerView.ViewHolder {
        private final ImageView FotoViaje;
        private final TextView Titulo, Descripcion;
        private final CardView CardViewHome;
        private final TextView Precio;
        private final TextView Dias;
        private final FloatingActionButton Fblike;

        public ReservasViewHolder(View view) {
            super(view);
            this.FotoViaje = (ImageView) view.findViewById(R.id.idImg);
            this.Titulo = (TextView) view.findViewById(R.id.tvTitulo);
            this.Descripcion = (TextView) view.findViewById(R.id.tvDescripcion);
            this.CardViewHome = (CardView) view.findViewById(R.id.EtiCardView);
            this.Precio = view.findViewById(R.id.tvPrecio);
            this.Dias = view.findViewById(R.id.tvDias);
            this.Fblike = view.findViewById(R.id.fbLike);
        }
        //RIGHT?!
        public ImageView getFotoViaje() {return FotoViaje;}
        public TextView getTitulo() {return Titulo;}
        public CardView getCardViewHome() {return CardViewHome;}
    }

    public List<HomeViajesRV> getListaViajes() {
        return listaViajes;
    }

    public void setListaViajes(List<HomeViajesRV> listaViajes) {
        this.listaViajes = listaViajes;
        notifyDataSetChanged();
    }
}
