package com.example.exc1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;

public class Activity_End_Game extends AppCompatActivity {
    ImageView end_IMG_background;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game);
        findviews();
    }

    private void findviews() {
        end_IMG_background = findViewById(R.id.end_IMG_background);

        setView();
    }

    private void setView() {
        Glide
                .with(this)
                .load(R.drawable.end_backgroud)
                .centerCrop()
                .into(end_IMG_background);


    }
}