package com.example.exc1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class Activity_End_Game extends AppCompatActivity {
    ImageView end_IMG_background;
    TextView end_TXT_winner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        setContentView(R.layout.activity_end_game);
        String winnerName = getIntent().getExtras().getString("WINNER_NAME");
        findviews();
        end_TXT_winner.setText(winnerName);
    }

    private void findviews() {
        end_IMG_background = findViewById(R.id.end_IMG_background);
        end_TXT_winner = findViewById(R.id.end_TXT_winner);
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