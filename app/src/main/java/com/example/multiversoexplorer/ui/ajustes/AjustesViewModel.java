package com.example.multiversoexplorer.ui.ajustes;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AjustesViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public AjustesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Mi cuenta");
    }
    public LiveData<String> getText() {return mText;}

}
