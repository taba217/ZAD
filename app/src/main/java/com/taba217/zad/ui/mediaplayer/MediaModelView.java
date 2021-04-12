package com.taba217.zad.ui.mediaplayer;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.taba217.zad.models.LectureItem;
import com.taba217.zad.models.Lecturer;

import java.util.ArrayList;
import java.util.List;

public class MediaModelView extends ViewModel {

    private final MutableLiveData<ArrayList<LectureItem>> categoryMutableLiveData;

    public MediaModelView() {
        categoryMutableLiveData = new MutableLiveData<>();
        getData();
    }

    private void getData() {
//        new Connect()
//        ArrayList<lecture> lectures = new ArrayList<>();
//        lectures.add(new lecture("محاضرات", "محاضرات", "1", 0, "الصلاة", "s", "24", "", 1));
//        lectures.add(new lecture("محاضرات", "محاضرات", "1", 0, "الصلاة", "s", "24", "", 1));
//        lectures.add(new lecture("محاضرات", "محاضرات", "1", 0, "الصلاة", "محاضرات", "24", "", 1));
//        lectures.add(new lecture("محاضرات", "محاضرات", "1", 0, "الصلاة", "محاضرات", "24", "", 1));
//        lectures.add(new lecture("محاضرات", "محاضرات", "1", 0, "الصلاة", "محاضرات", "24", "", 1));
//        lectures.add(new lecture("محاضرات", "محاضرات", "1", 0, "الصلاة", "محاضرات", "24", "", 1));
//        List<Lecturer> itemCategories = new ArrayList<>();
//        itemCategories.add(new Lecturer("title", "image", lectures));
//        categoryMutableLiveData.setValue(lectures);
    }

//    public LiveData<ArrayList<lecture>> getdata() {
//        return categoryMutableLiveData;
//    }


}