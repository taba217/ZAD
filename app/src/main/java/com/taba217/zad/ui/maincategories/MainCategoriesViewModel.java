package com.taba217.zad.ui.maincategories;

import android.os.CountDownTimer;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.taba217.zad.connction.Connect;
import com.taba217.zad.models.Lecturer;
import com.taba217.zad.models.Lecturer;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainCategoriesViewModel extends ViewModel {

     final MutableLiveData<List<Lecturer>> categoryMutableLiveData;
    List<Lecturer> itemCategories = new ArrayList<>();

    public MainCategoriesViewModel() {
        categoryMutableLiveData = new MutableLiveData<>();
    }

    public void getData() {
        new Connect()
                .connectTo()
                .getlatest()
                .enqueue(new Callback<List<Lecturer>>() {
                    @Override
                    public void onResponse(Call<List<Lecturer>> call, Response<List<Lecturer>> response) {
                        Log.i("lecture maincat success", response.body() + "");
                        Log.i("lecture request success", call.request() + "");
                        assert response.body() != null;
                        itemCategories.addAll(response.body());
                        categoryMutableLiveData.setValue(itemCategories);
                        Log.i("lecture request success", itemCategories.get(0).getName()+ "");

                    }

                    @Override
                    public void onFailure(Call<List<Lecturer>> call, Throwable t) {
                        Log.i("lecture success fail", t.getMessage() + "");
                        Log.i("lecture request error", call.request() + "");
                    }
                });
//        ArrayList<Lecture> lectures = new ArrayList<>();
//        lectures.add(new Lecture("محاضرات", "محاضرات", "1", 0, "الصلاة", "s", "24", "", 1));
//
//        itemCategories.add(new Lecturer("title", "image", lectures));

    }

    public LiveData<List<Lecturer>> getdata() {
        getData();
        return categoryMutableLiveData;
    }
}