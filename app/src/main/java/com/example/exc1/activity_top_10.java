package com.example.exc1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class activity_top_10 extends FragmentActivity implements OnMapReadyCallback {
    private TextView[] top10;
    private Button top10_BTN_menu;
    public final int TOP = 10;
    GoogleMap map;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_10);
        setView();
        ArrayList<HighScore> scores= Top_10.getInstance().getRecords();
    }

    @Override
    protected void onPause() {
        super.onPause();
        MyMediaPlayer.getInstance(this).pauseMusic();
    }

    @Override
    protected void onResume() {
        super.onResume();
        MyMediaPlayer.getInstance(this).playMusic();
    }


    private void updateMap() {
        ArrayList<HighScore> scores= Top_10.getInstance().getRecords();
        for(int i=0;i<scores.size();i++){
            if(scores.get(i).getLocation()!=null) {
                double scoreLat = scores.get(i).getLocation().getLat();
                double scoreLng = scores.get(i).getLocation().getLon();
                LatLng scoreLocation = new LatLng(scoreLat, scoreLng);
                map.addMarker(new MarkerOptions().position(scoreLocation).title("#"+(i + 1)+" " + scores.get(i).getName()));
                map.moveCamera(CameraUpdateFactory.newLatLng(scoreLocation));
            }
        }
    }

    private void setView() {
        top10_BTN_menu = findViewById(R.id.top10_BTN_menu);
        top10_BTN_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        TextView[] top10 = new TextView[TOP];
        top10[0] = findViewById(R.id.top10_TXT_1);
        top10[1] = findViewById(R.id.top10_TXT_2);
        top10[2] = findViewById(R.id.top10_TXT_3);
        top10[3] = findViewById(R.id.top10_TXT_4);
        top10[4] = findViewById(R.id.top10_TXT_5);
        top10[5] = findViewById(R.id.top10_TXT_6);
        top10[6] = findViewById(R.id.top10_TXT_7);
        top10[7] = findViewById(R.id.top10_TXT_8);
        top10[8] = findViewById(R.id.top10_TXT_9);
        top10[9] = findViewById(R.id.top10_TXT_10);
        ArrayList<HighScore> records = Top_10.getInstance().getRecords();
        for (int i = 0; i< records.size();i++){
            top10[i].setText(records.get(i).getName() + " : " + records.get(i).getAttacks());
        }
        SupportMapFragment mapFragment = (SupportMapFragment)getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map=googleMap;
        LatLng afeka = new LatLng(32.115033, 34.818040);
        map.addMarker(new MarkerOptions().position(afeka).title("afeka!"));
        map.moveCamera(CameraUpdateFactory.newLatLng(afeka));
        map.setMinZoomPreference(10);
        updateMap();

    }
}