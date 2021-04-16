package com.taba217.zad.ui.mediaplayer;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.exoplayer2.util.Log;
import com.taba217.zad.connction.Connect;
import com.taba217.zad.models.LectureItem;
import com.taba217.zad.models.Lecturer;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MediaModelView extends ViewModel {

    private final MutableLiveData<LectureItem> lectureItemMutableLiveData;

    public MediaModelView() {
        lectureItemMutableLiveData = new MutableLiveData<>();
    }

    public void getData(int id) {
            new Connect()
                    .connectTo()
                    .getlecture(id)//bundle.getInt("lecture_id"))
                    .enqueue(new Callback<LectureItem>() {
                        @Override
                        public void onResponse(Call<LectureItem> call, Response<LectureItem> response) {
                            Log.i("lecture", response.raw() + "");
//                            Log.i("lecture", response.body().getLectureSeries().get(0).getName());
                            lectureItemMutableLiveData.setValue(response.body());
                        }

                        @Override
                        public void onFailure(Call<LectureItem> call, Throwable t) {
                            Log.i("lecture", call.request() + "");
                            Log.i("lecture", t.getMessage() + "");
                        }
                    });
        }

    public LiveData<LectureItem> getdata(int id) {
        getData(id);
        return lectureItemMutableLiveData;
    }


}