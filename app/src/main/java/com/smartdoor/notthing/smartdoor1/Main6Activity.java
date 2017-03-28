package com.smartdoor.notthing.smartdoor1;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;

public class Main6Activity extends AppCompatActivity {

    AsyncHttpClient asynchttp;
    RequestParams tagparams;
    String http_status;
    String server_name = "http://smartdoor.hol.es/password_door.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main6);

        asynchttp = new AsyncHttpClient();
        tagparams = new RequestParams();


        Button btn_cancel = (Button)findViewById(R.id.cancel_btn);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main6Activity.this,Main5Activity.class);
                Toast.makeText(Main6Activity.this,"ยกเลิกแล้ว",Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });


        Button ok_btn = (Button) findViewById(R.id.ok_btn);
        ok_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText old_pass = (EditText) findViewById(R.id.editText2);
                EditText new_pass = (EditText) findViewById(R.id.editText5);
                EditText con_pass = (EditText) findViewById(R.id.editText6);

                String npass = new_pass.getText().toString();
                String cpass = con_pass.getText().toString();



                if (npass.equals(cpass)){
                    http_status = "door_pass";
                    tagparams.put("command", "door_pass");
                    tagparams.put("old_pass", old_pass.getText().toString());
                    tagparams.put("new_pass", npass);
                    tagparams.put("con_pass", cpass);
                    asynchttp.post(Main6Activity.this, server_name, tagparams, asyhttp_handle_door());
                }else {
                    Toast.makeText(Main6Activity.this,"รหัสไม่ต้องกัน",Toast.LENGTH_SHORT).show();
                }




            }
        });

    }

    AsyncHttpResponseHandler asyhttp_handle_door(){
        return new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                String massage = new String(bytes);
                Toast.makeText(Main6Activity.this,massage,Toast.LENGTH_SHORT).show();
                if (http_status.equals("door_pass")){
                    if (massage.equals("old_pass_ok")){
                        Toast.makeText(Main6Activity.this,"แก้ไขรหัสผ่านแล้ว",Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(Main6Activity.this,"รหัสเดิมไม่ถูกต้อง",Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {

            }
        };
    }



}
