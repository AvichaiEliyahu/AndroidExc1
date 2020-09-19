package com.example.exc1;

import android.content.Context;
import android.media.MediaPlayer;

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
    }

    public void pauseMusic(){
        this.mediaPlayer.pause();
    }

    public void resumeMusic(){
        this.mediaPlayer.reset();
    }

    public void stopMusic(){
        this.mediaPlayer.stop();
        this.mediaPlayer.release();
    }
}
