package com.example.exc1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.json.JSONObject;

public class Activity_End_Game extends AppCompatActivity {
    ImageView end_IMG_background;
    TextView end_TXT_winner;
    Button end_BTN_menu;
    Button end_BTN_T10;

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
                Intent intent = new Intent(Activity_End_Game.this,activity_top_10.class);
                startActivity(intent);
                finish();

            }
        });

        JSONObject records = MySP.getInstance().getJson(MySP.KEYS.TOP_10);

    }


}