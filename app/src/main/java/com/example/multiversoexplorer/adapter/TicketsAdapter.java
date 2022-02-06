package com.example.multiversoexplorer.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.multiversoexplorer.R;
import com.example.multiversoexplorer.ui.dashboard.DashboardViajesRV;
import com.example.multiversoexplorer.ui.dashboard.GuardarBilletesActivity;

import java.util.List;

public class TicketsAdapter  extends RecyclerView.Adapter<TicketsAdapter.TicketsViewHolder> {
    private List<DashboardViajesRV> lista;

    public TicketsAdapter(List<DashboardViajesRV> lista) {
        this.lista = lista;
    }

    @NonNull
    @Override
    public TicketsAdapter.TicketsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_dashboard, parent,false);
        return new TicketsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TicketsAdapter.TicketsViewHolder holder, int position) {
        DashboardViajesRV ticket = lista.get(position);
        holder.tvDestino.setText(ticket.getDestino());
        holder.tvFechaIda.setText(ticket.getFechaIda());
        holder.tvFechaVuelta.setText(ticket.getFechaVuelta());
        holder.cardview.setOnClickListener(view -> {
            holder.cardview.getContext().startActivity(
                    new Intent(holder.cardview.getContext(), GuardarBilletesActivity.class));
        });
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class TicketsViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvDestino;
        private final TextView tvFechaIda;
        private final TextView tvFechaVuelta;
        private View cardview;

        public TicketsViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDestino = itemView.findViewById(R.id.tvDestino);
            tvFechaIda = itemView.findViewById(R.id.tvFechaIda);
            tvFechaVuelta = itemView.findViewById(R.id.tvFechaVuelta);
            cardview = itemView;
        }
    }
}
