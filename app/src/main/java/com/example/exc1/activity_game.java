package com.example.exc1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

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

    private IPlayer turn;
    private boolean playerPlayed;


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
        turn = howIsStart();
        if(playerPlayed){
            turn = turn == player1 ? player2 : (player1);
        }
    }

    private IPlayer howIsStart() {
        return player1;
    }

    private void initPlayers() {
        player1 = new Player().setHP(1000).setName("PSG");
        game_BTN_player1_attack1.setOnClickListener(attackBTN(player2,10));
        game_BTN_player1_attack2.setOnClickListener(attackBTN(player2,20));
        game_BTN_player1_attack3.setOnClickListener(attackBTN(player2,40));


        player2 = new Player("BYM",1000);
        game_BTN_player2_attack1.setOnClickListener(attackBTN(player1,10));
        game_BTN_player2_attack2.setOnClickListener(attackBTN(player1,20));
        game_BTN_player2_attack3.setOnClickListener(attackBTN(player1,40));

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

    private View.OnClickListener attackBTN(final IPlayer defender, final int power){
        return new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                defender.attack(power);
                playerPlayed = true;
            }
        };
    }


}