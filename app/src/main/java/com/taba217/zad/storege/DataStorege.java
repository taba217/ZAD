package com.taba217.zad.storege;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import static android.content.Context.MODE_PRIVATE;

public class DataStorege {
    private SharedPreferences preferences;
    private Context context;
    private String preferenceFileName = "user_data";

    public DataStorege(Context context) {
        this.context = context;
        preferences = context.getSharedPreferences(preferenceFileName, MODE_PRIVATE);
    }

    public DataStorege setContext(Context context) {
        return new DataStorege(context);
    }

    public void save(String key, String value) {
        preferences.edit().putString(key, value).apply();
    }

    public void save(String key, int value) {
        preferences.edit().putInt(key, value).apply();
    }

    public void save(String key, boolean value) {
        preferences.edit().putBoolean(key, value).apply();
    }

    public String getString(String key) {
        return preferences.getString(key, null);
    }

    public String getString(String key, String def) {
        return preferences.getString(key, def);
    }

    public int getInt(String key) {
        return preferences.getInt(key, 0);
    }

    public int getInt(String key, int def) {
        return preferences.getInt(key, def);
    }

    public boolean getBoolean(String key) {
        return preferences.getBoolean(key, false);
    }

    public Object getObject(String account) {
        return getSavedObjectFromPreference(account, Object.class);
    }

    public void saveObjectToSharedPreference(String serializedObjectKey, Object object) {
        final Gson gson = new Gson();
        String serializedObject = gson.toJson(object);
        preferences.edit()
                .putString(serializedObjectKey, serializedObject)
                .apply();
    }

    public <GenericClass> GenericClass getSavedObjectFromPreference(String preferenceKey, Class<GenericClass> classType) {
        if (preferences.contains(preferenceKey)) {
            final Gson gson = new Gson();
            return gson.fromJson(getString(preferenceKey), classType);
        }
        return null;
    }
}
