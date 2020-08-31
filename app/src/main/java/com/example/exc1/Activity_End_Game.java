package com.example.exc1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class Activity_End_Game extends AppCompatActivity {
    private ImageView end_IMG_background;
    private TextView end_TXT_winner;
    private Button end_BTN_menu;
    private Button end_BTN_T10;
    private  Location winnerLocation = null;
    private HighScore highScore;
    // The minimum distance to change Updates in meters
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 1; // 10 meters
    // The minimum time between updates in milliseconds
    private static final long MIN_TIME_BW_UPDATES = 1; // 1 minute
    public static final String WINNER_NAME = "WINNER_NAME";
    public static final String WINNER_ATTACKS = "WINNER_ATTACKS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        LocationManager locationManager = (LocationManager)
                getSystemService(Context.LOCATION_SERVICE);
        LocationListener locationListener = new MyLocationListener();

        setContentView(R.layout.activity_end_game);
        String winnerName = getIntent().getExtras().getString(WINNER_NAME);
        int winnerAttacks = getIntent().getExtras().getInt(WINNER_ATTACKS);
        findviews();
        end_TXT_winner.setText(winnerName);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
            return;
        }
        locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER, 5000, 10, locationListener);
        checkForRecordAndReplace(new HighScore(winnerName,winnerAttacks,winnerLocation));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 1 && grantResults.length > 0){
    this.winnerLocation =null;
        }else {
            MySignalV2.getInstance(this).showToast("Location Denied");
        }
    }

    private final LocationListener mLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(final Location location) {
            //your code here
        }
    };

    private void findviews() {
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
    private void checkForRecordAndReplace(HighScore highScore) {
        Top_10.getInstance().
                checkForRecordAndReplace(highScore);
    }

}