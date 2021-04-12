package com.taba217.zad.connction;

import android.app.Dialog;
import android.content.Context;

import android.graphics.drawable.ColorDrawable;

import android.util.Log;
import android.view.Window;

import com.google.gson.JsonObject;
import com.taba217.zad.MainActivity;
import com.taba217.zad.R;
import com.taba217.zad.storege.DataStorege;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.taba217.zad.connction.BaseUrl.BASE_URL;


public class Connect {
    private Context context;
    public static Dialog dialog;

    public Connect(Context context) {
        this.context = context;
        loading();
    }

    public Connect() {
        storege = new DataStorege(MainActivity.context);
    }

    DataStorege storege;

    private void loading() {
        storege = new DataStorege(MainActivity.context);
        dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.trans_loading);
        dialog.setCancelable(true);
        dialog.show();
    }

    public Interface connectTo() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(Interface.class);
    }

}
