package com.example.exc1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationResult;

public class Activity_End_Game extends AppCompatActivity {
    private ImageView end_IMG_background;
    private TextView end_TXT_winner;
    private Button end_BTN_menu;
    private Button end_BTN_T10;
    // The minimum distance to change Updates in meters
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 1; // 10 meters
    private FusedLocationProviderClient mFusedLocationClient;
    private double lon, lat;
    private int PERMISSION_ID = 20;
    // The minimum time between updates in milliseconds
    public static final String WINNER_NAME = "WINNER_NAME";
    public static final String WINNER_ATTACKS = "WINNER_ATTACKS";
    private LocationCallback mLocationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(LocationResult locationResult) {
            Location mLastLocation = locationResult.getLastLocation();
            lat = mLastLocation.getLatitude();
            lon = mLastLocation.getLongitude();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_end_game);
        String winnerName = getIntent().getExtras().getString(WINNER_NAME);
        findViews();
        end_TXT_winner.setText(winnerName);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        MyMediaPlayer.getInstance(this).playMusic();
    }

    @Override
    protected void onPause() {
        super.onPause();
        MyMediaPlayer.getInstance(this).pauseMusic();

    }

    private void findViews() {
        end_IMG_background = findViewById(R.id.end_IMG_background);
        end_TXT_winner = findViewById(R.id.end_TXT_winner);
        end_BTN_menu = findViewById(R.id.end_BTN_menu);
        end_BTN_T10 = findViewById(R.id.end_BTN_T10);
        setView();
    }

    private void setView() {
        Glide
                .with(this)
                .load(R.drawable.end_backgroud)
                .centerCrop()
                .into(end_IMG_background);
        end_BTN_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        end_BTN_T10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Activity_End_Game.this, activity_top_10.class);
                startActivity(intent);
                finish();

            }
        });
    }
}