package com.smartdoor.notthing.smartdoor1;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

public class MainActivity extends AppCompatActivity {

    private Handler handler;
    private Runnable runnable;
    long deley_time;
    long time = 3500L;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent);
                finish();

            }
        };
    }

    @Override
    public void onResume(){
        super.onResume();
        deley_time = time;
        handler.postDelayed(runnable, deley_time);
        time = System.currentTimeMillis();

    }

    public void onPause(){
        super.onPause();
        handler.removeCallbacks(runnable);
        time = deley_time - (System.currentTimeMillis()-time);
    }


}

