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
        getData();
    }

    private void getData() {
            new Connect()
                    .connectTo()
                    .getlecture(1)//bundle.getInt("lecture_id"))
                    .enqueue(new Callback<LectureItem>() {
                        @Override
                        public void onResponse(Call<LectureItem> call, Response<LectureItem> response) {
                            lectureItemMutableLiveData.setValue(response.body());
                            Log.i("lecture", response.body().getName());
                            Log.i("lecture", response.raw() + "");
                        }

                        @Override
                        public void onFailure(Call<LectureItem> call, Throwable t) {
                            Log.i("lecture", call.request() + "");
                            Log.i("lecture", t.getMessage() + "");
                        }
                    });
        }

    public LiveData<LectureItem> getdata() {
        return lectureItemMutableLiveData;
    }


}