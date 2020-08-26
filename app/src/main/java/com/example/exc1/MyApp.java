package com.example.exc1;

import android.app.Application;

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        MySignalV2.initHelper(this);

        MySP.initHelper(this);

        Top_10.getInstance();
    }}
