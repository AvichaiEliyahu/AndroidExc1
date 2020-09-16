package com.example.exc1;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.ArraySet;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class MySP {


    public interface KEYS {
        public static final String TOP_10 = "TOP_10";
        public static final String USER_LAST_LOGIN_TIME_STAMP = "USER_LAST_LOGIN_TIME_STAMP";
        public static final String USER_MAIL = "USER_MAIL";
    }


    private static MySP instance;
    private SharedPreferences prefs;

    public static MySP getInstance() {
        return instance;
    }

    private MySP(Context context) {
        prefs = context.getApplicationContext().getSharedPreferences("MAIN_SP", Context.MODE_PRIVATE);
    }

    public static MySP initHelper(Context context) {
        if (instance == null)
            instance = new MySP(context);
        return instance;
    }

    public void putArray(String KEY, ArrayList<HighScore> value) {
        try{

        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<HighScore>>() {
        }.getType();
        String toSave = gson.toJson(value, type);

        Log.d("pttt", toSave);
        prefs.edit().putString(KEY, toSave).apply();

        }catch (Exception e){
            Log.d("error",e.getMessage());
        }
    }

    public ArrayList getArray(String KEY) {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<HighScore>>() {
        }.getType();

        String list = prefs.getString(KEY, null);
        if (list != null) {
            ArrayList<HighScore> listFromGson;
            listFromGson = gson.fromJson(list, type);
            Collections.sort(listFromGson);
            return listFromGson;
        }
        return new ArrayList<HighScore>();
    }

    public void putString(String key, String value) {
        prefs.edit().putString(key, value).apply();
    }

    public String getString(String key, String def) {
        return prefs.getString(key, def);
    }

    public void putBoolean(String key, boolean value) {
        prefs.edit().putBoolean(key, value).apply();
    }

    public boolean getBoolean(String key, boolean def) {
        return prefs.getBoolean(key, def);
    }

    public void putDouble(String KEY, double defValue) {
        putString(KEY, String.valueOf(defValue));
    }

    public double getDouble(String KEY, double defValue) {
        return Double.parseDouble(getString(KEY, String.valueOf(defValue)));
    }

    public void putInt(String KEY, int value) {
        prefs.edit().putInt(KEY, value).apply();
    }

    public int getInt(String KEY, int defValue) {
        return prefs.getInt(KEY, defValue);
    }

    public void putJson(String KEY, JSONObject json) {
        String toSave = json.toString();
        this.putString(KEY, toSave);
    }

    public JSONObject getJson(String KEY) {
        String stringRv = this.getString(KEY, "{}");
        try {
            return new JSONObject(stringRv);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new JSONObject();
    }
}
