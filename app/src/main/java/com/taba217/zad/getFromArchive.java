package com.taba217.zad;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.taba217.zad.connction.Connect;
import com.taba217.zad.connction.getUrl;
import com.taba217.zad.models.LectureItem;
import com.taba217.zad.models.LectureSeriesItem;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class getFromArchive {
    String url = "https://archive.org/details/sera-mohmed-said";
    LectureItem lecture;
    Context context;

    //                "https://archive.org/details/khodwat_mohamed_said_haj";
//    print("Fetching %s ...", url);
    getFromArchive(Context context, String Url) {
        this.url = "https://archive.org/details/sera-mohmed-said";
        this.context = context;
        lecture = new LectureItem();

        new getUrlFromGoogle().execute();
    }

    public class getUrlFromGoogle extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
//            getlec();
            return null;
        }
    }

    Document doc;
    String name;

    void getTitle() {
        Elements titles = doc.select("title");
        for (Element link : titles) {
            if (!link.text().contains("icon") && !link.text().contains("logo"))
                name = link.text();
        }
    }

    void getlec() {
        try {
            doc = Jsoup.connect(url).get();
        } catch (Exception e) {
            e.printStackTrace();
            getlec();
        }
        Elements links = doc.select("a[href]");
        StringBuilder names= new StringBuilder(),urls = new StringBuilder();

//        ArrayList<String> names=new ArrayList<>();
//        ArrayList<String> urls=new ArrayList<>();
        for (Element link : links) {
            if (link.attr("abs:href").contains(".mp3") && trim(link.text(), 100).replace("download", "").length() > 10) {
//                lecture.url = link.attr("abs:href");
//                lecture.lecName = trim(link.text(), 100).replace("download", "");
//                Log.i("all mp3 in site", String.format("%s  %s", lecture.getUrl(), lecture.getLecName()));

//                names.add(trim(link.text(), 100).replace("download", ""));
                urls.append(link.attr("abs:href")+",");
//                name.put(trim(link.text(), 100).replace("download", ""),link.attr("abs:href"));
                names.append(trim(link.text(), 100).replace("download", "")+",");
            }
        }
        new Connect()
                .connectTo()
                .addLecture("name",url,"1","1","1")
                .enqueue(new Callback<List<LectureSeriesItem>>() {
                    @Override
                    public void onResponse(Call<List<LectureSeriesItem>> call, Response<List<LectureSeriesItem>> response) {

                    }

                    @Override
                    public void onFailure(Call<List<LectureSeriesItem>> call, Throwable t) {

                    }
                });


    }

    private static String trim(String s, int width) {
        if (s.length() > width)
            return s.substring(0, width - 1) + ".";
        else
            return s;
    }
}
