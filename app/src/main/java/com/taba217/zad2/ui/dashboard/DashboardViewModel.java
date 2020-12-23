package com.taba217.zad2.ui.dashboard;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DashboardViewModel extends ViewModel {

    private MutableLiveData<String> Text;

    public DashboardViewModel() { Text = new MutableLiveData<>();
        Text.setValue("This is dashboard fragment");
    }

    public LiveData<String> getText() {
        return Text;
    }
}