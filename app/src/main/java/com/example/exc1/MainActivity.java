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
import com.bumptech.glide.annotation.GlideType;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    ImageView main_IMG_backgroundimg;
    Button main_BTN_start;
    Button main_BTN_top10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mediaPlayer = MediaPlayer.create(this,R.raw.soundtrack);
        mediaPlayer.start();
        setContentView(R.layout.activity_main);
        findviews();
        Glide
                .with(this)
                .load(R.drawable.main_bgr)
                .centerCrop()
                .into(main_IMG_backgroundimg);

        main_BTN_start.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
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
    protected void onDestroy() {
        super.onDestroy();
        mediaPlayer.pause();
    }

    private void openGameActivity() {
        Intent intent = new Intent(MainActivity.this,activity_game.class);
        startActivity(intent);
    }
    private void openTop10Activity() {
        Intent intent = new Intent(MainActivity.this,activity_top_10.class);
        startActivity(intent);
    }

    private void findviews() {
        main_IMG_backgroundimg = findViewById(R.id.main_IMG_backgroundimg);
        main_BTN_start = findViewById(R.id.main_BTN_start);
        main_BTN_top10 = findViewById(R.id.main_BTN_top10);
    }
    private void requestPermissions() {
        ActivityCompat.requestPermissions(
                this,
                new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},
                20
        );
    }
}
