package com.taba217.zad.connction;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;


import com.taba217.zad.MainActivity;
import com.taba217.zad.storege.DataStorege;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

import static com.taba217.zad.connction.BaseUrl.BASE_URL;


public class getUrl {
    static Document doc;

    public getUrl() {
        Log.i("geturl", "begins");
        new getUrlFromGoogle().execute();
    }

    public static class getUrlFromGoogle extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            String URL = "https://drive.google.com/file/d/1CTzzw1ffH__D6Nsyk6eUTbk7omTDwNSU/view?usp=sharing";
            try {
                doc = Jsoup.connect(URL).get();
            } catch (IOException e) {
                e.printStackTrace();
                try {
                    doc = Jsoup.connect(URL).get();
                } catch (IOException ex) {
                    ex.printStackTrace();
                    try {
                        doc = Jsoup.connect(URL).get();
                    } catch (IOException exc) {
                        exc.printStackTrace();
                    }
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (doc != null) {
                Log.i("address", doc.text());
                String[] url = doc.text().split("-");
                BASE_URL = "https://" + url[0].trim() + "/" + getClass().getPackage().getName() + "/";
                new DataStorege(MainActivity.context).save("url", BASE_URL);
                Log.i("address", BASE_URL);
            }
        }
    }
}
