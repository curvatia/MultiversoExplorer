package com.example.multiversoexplorer.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("FRAGMENT DE VIAJES EN CARDVIEW Y ELEGIR FAVORITOS");
    }

    public LiveData<String> getText() {
        return mText;
    }
}