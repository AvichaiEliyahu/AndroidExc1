package com.example.exc1;

import android.app.Application;
import android.media.MediaPlayer;

public class MyApp extends Application {
    MediaPlayer mediaPlayer;
    @Override
    public void onCreate() {
        super.onCreate();

        MySignalV2.getInstance(this);

        MySP.initHelper(this);

        Top_10.getInstance();

    }





}

