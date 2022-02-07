package com.example.multiversoexplorer.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.content.Intent;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.multiversoexplorer.R;
import com.example.multiversoexplorer.ui.dashboard.DashboardViajesRV;
import com.example.multiversoexplorer.ui.dashboard.DialogosBottom;
import com.example.multiversoexplorer.ui.dashboard.GuardarBilletesActivity;

import java.util.List;
import com.example.multiversoexplorer.ui.dashboard.DialogosBottom.dialogosBottomListener;
public class TicketsAdapter  extends RecyclerView.Adapter<TicketsAdapter.TicketsViewHolder> {
    private List<DashboardViajesRV> lista;
    private Context context;

    public TicketsAdapter(Context context, List<DashboardViajesRV> lista) {

        this.lista = lista;
        this.context = context;

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
        holder.cardview.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                DialogosBottom dialogosBottom = new DialogosBottom();
                dialogosBottom.show(((FragmentActivity)context).getSupportFragmentManager(),"ejemplo");
                dialogosBottom.setmListener(holder);
                return true;
            }
        });
        holder.cardview.setOnClickListener(view -> {
            holder.cardview.getContext().startActivity(
                    new Intent(holder.cardview.getContext(), GuardarBilletesActivity.class));
        });
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class TicketsViewHolder extends RecyclerView.ViewHolder implements DialogosBottom.dialogosBottomListener{
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

        @Override
        public void onButtonClicked(String text) {
            String hola;
        }
    }
}
