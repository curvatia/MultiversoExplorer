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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DashboardViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private MutableLiveData<List<DashboardViajesRV>> listaViajesCreados;
    private FirebaseAuth mAuth;
    DatabaseReference miDatabaseReference=
            FirebaseDatabase.getInstance().getReference();

    public void observarViajes(){
        FirebaseDatabase.getInstance()
                .getReference("userdata/"+mAuth.getCurrentUser().getUid()+"/viajesCreados")
                .addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        Map<String,Object> ticket = (Map<String,Object>) snapshot.getValue();
                        listaViajesCreados.getValue().add(
                                new DashboardViajesRV(
                                        ticket.get("img").toString(),
                                        ticket.get("destino").toString(),
                                        ticket.get("fechaIda").toString(),
                                        ticket.get("fechaVuelta").toString()));
                        listaViajesCreados.setValue(listaViajesCreados.getValue());
                    }
                    @Override
                    public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {}
                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot snapshot) {}
                    @Override
                    public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {}
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {}
                });
    }

    public DashboardViewModel() {
        mText = new MutableLiveData<>();
        listaViajesCreados = new MutableLiveData<>(new ArrayList<>());
        mAuth = FirebaseAuth.getInstance();
        mAuth.addAuthStateListener(new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser()!=null) {
                    observarViajes();
                }
            }
        });

        mText.setValue("FRAGMENT PARA CREAR VIAJES FORMULARIO FECHAS BOTON+ Y QR VUELOS");

    }

    public LiveData<String> getText() {
        return mText;
    }
    public LiveData<List<DashboardViajesRV>> getListaViajesCreados() {
        return listaViajesCreados;
    }
}