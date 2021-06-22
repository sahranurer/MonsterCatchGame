package com.sahraer.catchamonster;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView timeText;
    TextView scoreText;
    int score;
    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView9;
    ImageView imageView10;
    ImageView imageView11;
    ImageView imageView12;
    ImageView imageView14;

    SharedPreferences sharedPreferences;

    ImageView imageArray[];
    Handler handler;
    Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timeText = findViewById(R.id.timeText);
        scoreText = findViewById(R.id.scoreText);
        imageView1 = findViewById(R.id.imageView1);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4);
        imageView5 = findViewById(R.id.imageView5);
        imageView6 = findViewById(R.id.imageView6);
        imageView7 = findViewById(R.id.imageView7);
        imageView9 = findViewById(R.id.imageView9);
        imageView10 = findViewById(R.id.imageView10);
        imageView11 = findViewById(R.id.imageView11);
        imageView12 = findViewById(R.id.imageView12);
        imageView14 = findViewById(R.id.imageView14);
        score=0;
        imageArray = new ImageView[] {imageView1,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView9,imageView10,imageView11,imageView12,imageView14};
        hideImages();
        sharedPreferences = this.getSharedPreferences("com.sahraer.catchamonster", Context.MODE_PRIVATE);
        String scoreData = sharedPreferences.getString("scoreKey","Not Score");



        new CountDownTimer(10000,1000){

            @Override
            public void onTick(long millisUntilFinished) {
                timeText.setText("Time: "+millisUntilFinished/1000);

            }

            @Override
            public void onFinish() {
                timeText.setText("Finished!!");
                handler.removeCallbacks(runnable);
                for (ImageView imageView : imageArray){
                    imageView.setVisibility(View.INVISIBLE);
                }
                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Restart?");
                alert.setMessage("Yeniden oynamak ister misiniz?");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //restart
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                    }
                });
                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(MainActivity.this,"Oyun Bitti",Toast.LENGTH_LONG).show();
                    }
                });
                alert.show();

            }
        }.start();
    }
    public void skoruArttır(View view){
          score++;
          scoreText.setText("Score: "+score);


    }
    public void hideImages(){

        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                for (ImageView imageView : imageArray){
                    imageView.setVisibility(View.INVISIBLE);
                }
                Random random = new Random();
                int i = random.nextInt(10);
                imageArray[i].setVisibility(View.VISIBLE);
                handler.postDelayed(this,500);

            }
        };
        handler.post(runnable);



    }
    
}