package com.example.multiversoexplorer.ui.dashboard;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DashboardViewModel extends ViewModel {
    private MutableLiveData<List<DashboardViajesRV>> listaViajesCreados;
    private FirebaseAuth mAuth;
    private DatabaseReference refUserdata;
    private DatabaseReference refUID;
    private DatabaseReference reference;

    public DashboardViewModel() {
        refUserdata = FirebaseDatabase.getInstance().getReference().child("userdata");
        listaViajesCreados = new MutableLiveData<>(new ArrayList<>());
        mAuth = FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser()!=null) {
            refUID = refUserdata.child(mAuth.getCurrentUser().getUid());
            reference = refUID.child("viajesCreados");
            observarViajes();
        }
        mAuth.addAuthStateListener(new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser()!=null) {
                    refUID = refUserdata.child(mAuth.getCurrentUser().getUid());
                    reference = refUID.child("viajesCreados");
                    observarViajes();
                }
            }
        });
    }

    public void observarViajes(){
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(!snapshot.exists()) {
                    listaViajesCreados.setValue(new ArrayList<>());
                } else {
                    List<DashboardViajesRV> viajesLocal = new ArrayList<>();
                    List<Map<String, String>> ticketList = (List<Map<String, String>>) snapshot.getValue();
                    for (Map<String, String> ticket : ticketList) {
                        viajesLocal.add(new DashboardViajesRV(
                                ticket.get("img"),
                                ticket.get("destino"),
                                ticket.get("fechaIda"),
                                ticket.get("fechaVuelta")));
                    }
                    listaViajesCreados.setValue(viajesLocal);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });
    }

    public LiveData<List<DashboardViajesRV>> getListaViajesCreados() {
        return listaViajesCreados;
    }
}