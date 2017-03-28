package com.smartdoor.notthing.smartdoor1;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Checkable;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import cz.msebera.android.httpclient.Header;

import static com.smartdoor.notthing.smartdoor1.R.id.button_login;
import static com.smartdoor.notthing.smartdoor1.R.id.password;

public class Main2Activity extends AppCompatActivity {

    AsyncHttpClient asynchttp;
    RequestParams tagparams;
    String http_status;
    String server_name = "http://smartdoor.hol.es/password_door.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main2);

        asynchttp = new AsyncHttpClient();
        tagparams = new RequestParams();

        Button btn_login = (Button)findViewById(R.id.button_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText user = (EditText) findViewById(R.id.username);
                EditText pass = (EditText) findViewById(R.id.password);
                http_status = "login";
                tagparams.put("command", "login");
                tagparams.put("username", user.getText().toString());
                tagparams.put("password", pass.getText().toString());

                asynchttp.post(Main2Activity.this, server_name, tagparams, asyhttp_handle());
            }
        });

    }

    AsyncHttpResponseHandler asyhttp_handle(){
        return new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                String message = new String(bytes);
                Toast.makeText(Main2Activity.this,message,Toast.LENGTH_SHORT).show();
                if (http_status.equals("login")){
                    if(message.equals("login_ok")){
                        Toast.makeText(Main2Activity.this, "เข้าสู่ระบบสำเร็จ",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Main2Activity.this,Main5Activity.class);
                        startActivity(intent);

                    }else {
                        Toast.makeText(Main2Activity.this, "เข้าสู่ระบบไม่ได้",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Main2Activity.this,Main3Activity.class);
                        startActivity(intent);

                    }
                }
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {

            }
        };

    }


}

