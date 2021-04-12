package com.taba217.zad.connction;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.taba217.zad.models.LectureSeriesItem;
import com.taba217.zad.models.Lecturer;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;


public interface Interface {

    @GET("lecture/add")
//    @FormUrlEncoded
    Call<List<LectureSeriesItem>> addLecture(@Query("name") String name,
                                       @Query("url") String url,
                                       @Query("type_id") String type_id,
                                       @Query("image") String image,
                                       @Query("lecturer") String lecturer);// @Query("urls") ArrayList<String> urls);
    @GET("lecture/news")
    Call<List<Lecturer>> getlatest();
//    ") ArrayList<String> learning_objective_uuids, @Field("user_uuids[]") ArrayList<String> user_uuids, @Field("note") String note,

//    @GET("sendReport.php")
//    Call<ResponseBody> sendReport(@Query("error") String error);
//
//    @GET("checkupdate.php")
//    Call<JsonObject> checkUpdate();
//
//    @GET("getOffers.php")
//    Call<Offers> getOffers();
//
//    @GET("getrestrants.php")
//    Call<List<Restrant>>  getrestrants(@Query("type") int i);



}
