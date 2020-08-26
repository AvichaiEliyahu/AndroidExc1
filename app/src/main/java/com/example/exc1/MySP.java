package com.example.exc1;

import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONException;
import org.json.JSONObject;

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
