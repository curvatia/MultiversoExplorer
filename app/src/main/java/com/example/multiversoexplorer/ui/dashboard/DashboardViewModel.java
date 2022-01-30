package com.example.multiversoexplorer.ui.dashboard;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DashboardViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public DashboardViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("FRAGMENT PARA CREAR VIAJES FORMULARIO FECHAS BOTON+ Y QR VUELOS");
    }

    public LiveData<String> getText() {
        return mText;
    }
}