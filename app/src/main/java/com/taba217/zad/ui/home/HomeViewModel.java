package com.taba217.zad.ui.home;

import android.os.CountDownTimer;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<List<ItemCategory>> categoryMutableLiveData;

    public HomeViewModel() {
        categoryMutableLiveData = new MutableLiveData<>();
        getData();
    }

    private void getData() {
//        new Connect()
        ArrayList<LecItem> lecItems = new ArrayList<>();
        lecItems.add(new LecItem("محاضرات", "محاضرات", "1", 0, "الصلاة", "s", "24", "", 1));
        lecItems.add(new LecItem("محاضرات", "محاضرات", "1", 0, "الصلاة", "s", "24", "", 1));
        lecItems.add(new LecItem("محاضرات", "محاضرات", "1", 0, "الصلاة", "محاضرات", "24", "", 1));
        lecItems.add(new LecItem("محاضرات", "محاضرات", "1", 0, "الصلاة", "محاضرات", "24", "", 1));
        lecItems.add(new LecItem("محاضرات", "محاضرات", "1", 0, "الصلاة", "محاضرات", "24", "", 1));
        lecItems.add(new LecItem("محاضرات", "محاضرات", "1", 0, "الصلاة", "محاضرات", "24", "", 1));
        List<ItemCategory> itemCategories = new ArrayList<>();
        itemCategories.add(new ItemCategory("title", "image", lecItems));
        categoryMutableLiveData.setValue(itemCategories);

        new CountDownTimer(2000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {

                itemCategories.add(new ItemCategory("title", "image", lecItems));
                categoryMutableLiveData.setValue(itemCategories);
            }
        }.start();
    }

    public LiveData<List<ItemCategory>> getdata() {
        return categoryMutableLiveData;
    }


}