package com.example.exc1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {
    private ImageView main_IMG_background;
    private Button main_BTN_start;
    private Button main_BTN_top10;
    private boolean isOnlyActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        setContentView(R.layout.activity_main);
        findviews();
        Glide
                .with(this)
                .load(R.drawable.main_bgr)
                .centerCrop()
                .into(main_IMG_background);

        main_BTN_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGameActivity();
            }
        });
        main_BTN_top10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openTop10Activity();
            }
        });
        requestPermissions();
    }

    @Override
    protected void onStart() {
        super.onStart();
        MyMediaPlayer.getInstance(this).playMusic();
        isOnlyActivity = true;

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        MyMediaPlayer.getInstance(this).stopMusic();
    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onStop() {
        super.onStop();
        if (isOnlyActivity) {
            MyMediaPlayer.getInstance(this).pauseMusic();
        }
    }

        @Override
        protected void onPostResume () {
            super.onPostResume();
//            MyMediaPlayer.getInstance(this).resumeMusic();
        }


        private void openGameActivity () {
            isOnlyActivity = false;
            Intent intent = new Intent(MainActivity.this, activity_game.class);
            startActivity(intent);
        }

        private void openTop10Activity () {
            isOnlyActivity = false;
            Intent intent = new Intent(MainActivity.this, activity_top_10.class);
            startActivity(intent);
        }

        private void findviews () {
            main_IMG_background = findViewById(R.id.main_IMG_backgroundimg);
            main_BTN_start = findViewById(R.id.main_BTN_start);
            main_BTN_top10 = findViewById(R.id.main_BTN_top10);
        }

        private void requestPermissions () {
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},
                    20
            );
        }
    }
