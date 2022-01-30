package com.example.multiversoexplorer.ui.notifications;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NotificationsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public NotificationsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("FRAGMENT DE VIAJES FAVORITOS DESDE HOME");
    }

    public LiveData<String> getText() {
        return mText;
    }
}