package com.project.luulinhson.oderfood.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.project.luulinhson.oderfood.R;

public class WaitingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiting);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences spdangnhap = getSharedPreferences("trangthaidangnhap", Context.MODE_PRIVATE);
                boolean trangthai = spdangnhap.getBoolean("dangnhap",false);
                if(trangthai == true){
                    Intent intent = new Intent(WaitingActivity.this,TrangChuActivity.class);
                    intent.putExtra("tendn","");
                    startActivity(intent);
                    finish();
                }else {
                    Intent intent = new Intent(WaitingActivity.this,DangNhapActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        },3000);


    }
}
