package com.example.exc1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class activity_top_10 extends AppCompatActivity implements OnMapReadyCallback {

    GoogleMap map;
    private TextView[] top10;
    private Button top10_BTN_menu;
    public final int TOP = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapFragment);
        mapFragment.getMapAsync(this);
        setContentView(R.layout.activity_top_10);
        setView();
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
        for (int i = 0; i< records.size();i++) {
            top10[i].setText(records.get(i).getName());
        }
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        ArrayList<HighScore> top10 = Top_10.getInstance().getRecords();
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(31.4117257, 35.0818155), 6));
        for (int i = 0; i < top10.size(); i++) {
            if (top10.get(i).getLocation().getLatitude() != 0.0 && top10.get(i).getLocation().getLongitude() != 0.0) {
                map.addMarker(new MarkerOptions().position(new LatLng(top10.get(i).getLocation().getLatitude(), top10.get(i).getLocation().getLongitude()))).setTitle((i + 1) + ". " + top10.get(i).getName());
            }
        }
    }
}