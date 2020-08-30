package com.example.exc1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

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
    private TextView game_TXT_message;
    private ImageButton game_BTN_roll;
    private ImageView game_IMG_cubeP1;
    private ImageView game_IMG_cubeP2;
    private Timer tmr = new Timer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_game);
        findViews();
        initGameFunctions();
        initPlayers();
    }

    private void initGameFunctions() {
        game_BTN_roll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                howIsStart();
                startTimerTask();
            }
        });
    }


    private void randomPress(IPlayer player) {
        Random rnd = new Random();
        int randomAttack = rnd.nextInt(3); //random number 1-3
        if (player == player1) {
            p1BTNsArr.get(randomAttack).performClick();
        } else
            p2BTNsArr.get(randomAttack).performClick();

    }

    private void setBTNs(IPlayer defenderTurn) {
        if (defenderTurn == player1) {
            for (Button b : p2BTNsArr) {
                b.setClickable(true);
                b.setBackgroundColor(Color.RED);
            }
            for (Button b : p1BTNsArr) {
                b.setClickable(false);
                b.setBackgroundColor(Color.GRAY);
            }
        } else {
            for (Button b : p1BTNsArr) {
                b.setClickable(true);
                b.setBackgroundColor(Color.BLUE);
            }
            for (Button b : p2BTNsArr) {
                b.setClickable(false);
                b.setBackgroundColor(Color.GRAY);
            }
        }
    }

    private IPlayer howIsStart() {
        //TODO add images
        Random rand = new Random();
        int num1 = rand.nextInt(7);
        int num2 = rand.nextInt(7);
        game_BTN_roll.setVisibility(View.GONE);
        game_IMG_cubeP1.setImageResource(getImageByRand(num1));
        game_IMG_cubeP1.setVisibility(View.VISIBLE);
        game_IMG_cubeP2.setImageResource(getImageByRand(num2));
        game_IMG_cubeP2.setVisibility(View.VISIBLE);
        this.defenderTurn = num1 > num2 ? player1 : player2;
        setBTNs(defenderTurn);
        return defenderTurn;
    }

    private int getImageByRand(int randomNumber) {
        Resources res = getResources();
        String mDrawableName = "ic_dice" + randomNumber + ".xml";
        int resourceId = this.getResources().getIdentifier(mDrawableName, "drawable", getPackageName());
        return resourceId;
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
        game_progressBar_player1.setMax(player1.getHP());


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
        game_progressBar_player2.setMax(player2.getHP());

        updateProgBar();

    }

    private void findViews() {
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

        game_TXT_message = findViewById(R.id.game_TXT_message);
        game_BTN_roll = findViewById(R.id.game_BTN_roll);
        game_IMG_cubeP1 = findViewById(R.id.game_IMG_cubeP1);
        game_IMG_cubeP2 = findViewById(R.id.game_IMG_cubeP2);
    }

    private void attackBTN(int power) {
//        Log.d("pttt", "turn = " + defenderTurn + "\npower = " + power);
        boolean rv = defenderTurn.attack(power);
//        Log.d("pttt", "def HP = " + defenderTurn);
        changeTurn(rv);
    }

    private void changeTurn(boolean defenderAlive) {
        IPlayer attacker = defenderTurn == player1 ? player2 : (player1);
        int numOfAttacks = attacker.addToAttackCounter();
        if (defenderAlive) {
            defenderTurn = defenderTurn == player1 ? player2 : (player1);
            setBTNs(defenderTurn);
        } else {
            String winnerName = (defenderTurn == player1 ? player2.getName() : player1.getName());
            game_TXT_message.setText("The Winner Is " + winnerName);
            checkForHighRecord(attacker, numOfAttacks);
            openEndgameActivity(winnerName);
        }
        updateProgBar();
    }

    private void checkForHighRecord(IPlayer attacker, int numOfAttacks) {
        Top_10.getInstance().
                checkForRecordAndReplace(new HighScore(attacker.getName(), numOfAttacks, null));
    }

    private void updateProgBar() {
        game_progressBar_player1.setProgress(player1.getHP());
        game_progressBar_player2.setProgress(player2.getHP());
    }

    private void openEndgameActivity(String winnerName) {
        Intent intent = new Intent(activity_game.this, Activity_End_Game.class);
        intent.putExtra("WINNER_NAME", winnerName);
        startActivity(intent);
        finish();
    }

    private void startTimerTask() {
        tmr.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                // Return to UI Thread
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (defenderTurn.getHP() > 0)
                            randomPress(defenderTurn);
                        else tmr.cancel();
                    }
                });
            }
        }, 0, 1000);
    }

//    private void startCountDownTimer() {
//        new CountDownTimer(3000, 1000) {
//            public void onTick(long millisUntilFinished) {
//            }
//            public void onFinish() {
//            }
//        }.start();
//    }
}