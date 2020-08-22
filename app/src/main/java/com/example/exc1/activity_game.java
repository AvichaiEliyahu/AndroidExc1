package com.example.exc1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.util.ArrayList;

public class activity_game extends AppCompatActivity {
    private IPlayer player1;
    private ImageView game_IMG_player1;
    private Button game_BTN_player1_attack1;
    private Button game_BTN_player1_attack2;
    private Button game_BTN_player1_attack3;
    private ProgressBar game_progressBar_player1;

    private IPlayer player2;
    private ImageView game_IMG_player2;
    private Button game_BTN_player2_attack1;
    private Button game_BTN_player2_attack2;
    private Button game_BTN_player2_attack3;
    private ProgressBar game_progressBar_player2;

    private IPlayer defenderTurn;

    private ArrayList<Button> p1BTNsArr;
    private ArrayList<Button> p2BTNsArr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_game);
        findVIews();
        initPlayers();
        playGame();
    }

    private void playGame() {
        defenderTurn = howIsStart();
        setBTNs(defenderTurn);

    }

    private void setBTNs(IPlayer turnPlayer) {
        if (turnPlayer == player1) {
            for (Button b : p1BTNsArr) {
                b.setClickable(false);
                b.setBackgroundColor(Color.GRAY);
            }
            for (Button b : p2BTNsArr) {
                b.setClickable(true);
                b.setBackgroundColor(Color.RED);
            }
        } else {
            for (Button b : p2BTNsArr) {
                b.setClickable(false);
                b.setBackgroundColor(Color.GRAY);
            }
            for (Button b : p1BTNsArr) {
                b.setClickable(true);
                b.setBackgroundColor(Color.BLUE);

            }
        }
    }

    private IPlayer howIsStart() {
//        int num1 = (int)Math.random()*7;
//        int num2 = (int)Math.random()*7;
//        return num1 > num2 ? player1 : player2;
        return player1;
    }

    private void initPlayers() {
        player1 = new Player().setHP(100).setName("PSG");
        p1BTNsArr = new ArrayList<>();
        p1BTNsArr.add(game_BTN_player1_attack1);
        p1BTNsArr.add(game_BTN_player1_attack2);
        p1BTNsArr.add(game_BTN_player1_attack3);
        game_BTN_player1_attack1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attackBTN(10);
            }
        });
        game_BTN_player1_attack2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attackBTN(20);
            }
        });
        game_BTN_player1_attack3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attackBTN(40);
            }
        });


        p2BTNsArr = new ArrayList<>();
        p2BTNsArr.add(game_BTN_player2_attack1);
        p2BTNsArr.add(game_BTN_player2_attack2);
        p2BTNsArr.add(game_BTN_player2_attack3);
        player2 = new Player("BYM", 100);
        game_BTN_player2_attack1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attackBTN(10);
            }
        });
        game_BTN_player2_attack2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attackBTN(20);
            }
        });
        game_BTN_player2_attack3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attackBTN(40);
            }
        });
    }

    private void findVIews() {
        //player1
        game_IMG_player1 = findViewById(R.id.game_IMG_player1);
        game_BTN_player1_attack1 = findViewById(R.id.game_BTN_player1_attack1);
        game_BTN_player1_attack2 = findViewById(R.id.game_BTN_player1_attack2);
        game_BTN_player1_attack3 = findViewById(R.id.game_BTN_player1_attack3);
        game_progressBar_player1 = findViewById(R.id.game_progressBar_player1);

        //player2
        game_IMG_player2 = findViewById(R.id.game_IMG_player2);
        game_BTN_player2_attack1 = findViewById(R.id.game_BTN_player2_attack1);
        game_BTN_player2_attack2 = findViewById(R.id.game_BTN_player2_attack2);
        game_BTN_player2_attack3 = findViewById(R.id.game_BTN_player2_attack3);
        game_progressBar_player2 = findViewById(R.id.game_progressBar_player2);
    }

    private void attackBTN(int power) {
        Log.d("pttt", "power = " + power);
        boolean rv = defenderTurn.attack(power);
        Log.d("pttt", "def HP = " + defenderTurn.getHP());
        changeTurn(rv);
    }

    private void changeTurn(boolean defenderAlive) {
        if (defenderAlive) {
            defenderTurn = defenderTurn == player1 ? player2 : (player1);
            setBTNs(defenderTurn);
        } else {
            defenderTurn = defenderTurn == player1 ? player2 : (player1);

            Log.d("end", "game done with = " + defenderTurn);
        }
    }


}