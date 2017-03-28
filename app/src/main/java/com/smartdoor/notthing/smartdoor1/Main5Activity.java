package com.smartdoor.notthing.smartdoor1;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main5Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main5);


    }

    public void goto_activity6(View view){
        Intent intent = new Intent(this,Main6Activity.class);
        startActivity(intent);
    }

    public void goto_activity4(View view){
        Intent intent = new Intent(this,Main6Activity.class);
        startActivity(intent);
    }

    public void goto_logactivity(View view){
        Intent intent = new Intent(this,Main6Activity.class);
        startActivity(intent);
    }


}
