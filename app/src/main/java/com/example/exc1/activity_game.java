package com.example.exc1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class activity_game extends AppCompatActivity {
    private boolean isNextMove;
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

    /////// location //////////
    private FusedLocationProviderClient mFusedLocationClient;
    private double lon, lat;
    private int PERMISSION_ID = 20;
    private LocationCallback mLocationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(LocationResult locationResult) {
            Location mLastLocation = locationResult.getLastLocation();
            lat = mLastLocation.getLatitude();
            lon = mLastLocation.getLongitude();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isNextMove = false;

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_game);
        findViews();
        initGameFunctions();
        initPlayers();

    }



    @Override
    protected void onResume() {
        super.onResume();
        MyMediaPlayer.getInstance(this).playMusic();
    }

    @Override
    protected void onPause() {
        super.onPause();
        MyMediaPlayer.getInstance(this).pauseMusic();

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        MyMediaPlayer.getInstance(this).resumeMusic();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private void initGameFunctions() {
        game_BTN_roll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                howIsStart();

                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startTimerTask();
                    }
                }, 3000);

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
        Random rand = new Random();
        int num1 = rand.nextInt(6) + 1;
        int num2 = rand.nextInt(6) + 1;

        game_BTN_roll.setVisibility(View.GONE);
        setImageByRand(num1, game_IMG_cubeP2);
        game_IMG_cubeP1.setVisibility(View.VISIBLE);
        setImageByRand(num2, game_IMG_cubeP1);
        game_IMG_cubeP2.setVisibility(View.VISIBLE);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                game_IMG_cubeP1.setVisibility(View.GONE);
                game_IMG_cubeP2.setVisibility(View.GONE);
            }
        }, 3000);

        this.defenderTurn = num1 > num2 ? player1 : player2;
        setBTNs(defenderTurn);
        return defenderTurn;
    }

    private void setImageByRand(int randomNumber, ImageView imgCube) {
        Log.d("num", randomNumber + "");
        Context context = imgCube.getContext();
        String picName = "ic_dice" + randomNumber;
        int id = context.getResources().getIdentifier(picName, "drawable", context.getPackageName());
        imgCube.setImageResource(id);
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
        boolean rv = defenderTurn.attack(power);
        changeTurn(rv);
    }

    private void changeTurn(boolean defenderAlive) {
        IPlayer attacker = defenderTurn == player1 ? player2 : (player1);
        int numOfAttacks = attacker.addToAttackCounter();
        if (defenderAlive) {
            defenderTurn = defenderTurn == player1 ? player2 : (player1);
            setBTNs(defenderTurn);
        } else {
            Log.d("hello", "hello!");
            game_TXT_message.setText("The Winner Is " + attacker.getName());
            HighScore score = new HighScore(numOfAttacks, attacker.getName(), null, null);
//            Top_10.getInstance().checkForRecordAndReplace(score);
            //////// location ///////
            mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

            getLocationAndAddToTop10(attacker.getName(), numOfAttacks);
            openEndgameActivity(attacker);
        }
        updateProgBar();
    }

    ///////////// location functions //////////////
    private void getLocationAndAddToTop10(final String name, final int attacks) {
        if (checkPermissions()) {
            if (isLocationEnabled()) {
                mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    this.requestPermissions();
                    return;
                }
                mFusedLocationClient.getLastLocation().addOnCompleteListener(
                        new OnCompleteListener<Location>() {
                            @Override
                            public void onComplete(@NonNull Task<Location> task) {
                                Location location = task.getResult();
                                if (location == null) {
                                    requestNewLocationData();
                                } else {
                                    lat = location.getLatitude();
                                    lon = location.getLongitude();
                                }
                                Top_10.getInstance().checkForRecordAndReplace(new HighScore(attacks, name, lat, lon));
                            }
                        }
                );
            } else {
                MySignalV2.getInstance(this).showToast("please enable location service");
            }
        } else {
            requestPermissions();
        }
    }

    private void requestNewLocationData() {
        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(0);
        mLocationRequest.setFastestInterval(0);
        mLocationRequest.setNumUpdates(1);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mFusedLocationClient.requestLocationUpdates(
                mLocationRequest, mLocationCallback,
                Looper.myLooper()
        );
    }

    private boolean checkPermissions() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        return false;
    }

    private boolean isLocationEnabled() {
        LocationManager locationManager = (LocationManager) getSystemService(this.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
                LocationManager.NETWORK_PROVIDER
        );
    }

    private final LocationListener mLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(final Location location) {
            //your code here
        }
    };

    private void requestPermissions() {
        ActivityCompat.requestPermissions(
                this,
                new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},
                PERMISSION_ID
        );
    }


    //-----------  location functions -------------//
    private void updateProgBar() {
        game_progressBar_player1.setProgress(player1.getHP());
        game_progressBar_player2.setProgress(player2.getHP());
    }

    private void openEndgameActivity(IPlayer winner) {
        isNextMove = true;
        Intent intent = new Intent(activity_game.this, Activity_End_Game.class);
        intent.putExtra(Activity_End_Game.WINNER_NAME, winner.getName());
        intent.putExtra(Activity_End_Game.WINNER_ATTACKS, winner.getNumOfAttacks());
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


}