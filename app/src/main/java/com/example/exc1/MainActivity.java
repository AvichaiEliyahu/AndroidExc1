package com.example.exc1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.annotation.GlideType;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    ImageView main_IMG_backgroundimg;
    Button main_BTN_start;
    Button main_BTN_top10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findviews();
        Glide
                .with(this)
                .load(R.drawable.main_bgr)
                .centerCrop()
                .into(main_IMG_backgroundimg);
    }

    private void findviews() {
        main_IMG_backgroundimg = findViewById(R.id.main_IMG_backgroundimg);
        main_BTN_start = findViewById(R.id.main_BTN_start);
        main_BTN_top10 = findViewById(R.id.main_BTN_top10);
    }
}
