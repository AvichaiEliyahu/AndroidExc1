package com.example.exc1;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;

public class MyMediaPlayer {
    private MediaPlayer mediaPlayer;
    private static MyMediaPlayer instance;
    private static Context appContext;

    public static MyMediaPlayer getInstance(Context context) {
        if (instance == null) {
            instance = new MyMediaPlayer(context);
        }
        return instance;
    }

    private MyMediaPlayer(Context context) {
        appContext = context;
        mediaPlayer = MediaPlayer.create(appContext, R.raw.soundtrack);
        mediaPlayer.start();

    }

    public void playMusic(){
        mediaPlayer.start();
        Log.d("stop","play music");
    }

    public void pauseMusic(){
        this.mediaPlayer.pause();
        Log.d("stop"," pauseMusic");

    }

    public void resumeMusic(){
        this.mediaPlayer.start();
        Log.d("stop"," resumeMusic");
    }

    public void stopMusic(){
        this.mediaPlayer.stop();
        this.mediaPlayer.release();
        Log.d("stop"," stopMusic");
    }
}
