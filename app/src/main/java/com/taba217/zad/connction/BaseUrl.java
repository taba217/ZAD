package com.taba217.zad.connction;


import com.taba217.zad.MainActivity;
import com.taba217.zad.storege.DataStorege;

public class BaseUrl {
    public static String BASE_URL = new DataStorege(MainActivity.context).getString("url","http://192.168.43.202/zad/zad/public/")  ;
}
