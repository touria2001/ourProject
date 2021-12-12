package com.example.myapplication4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AnimationLoginActivity extends AppCompatActivity {
    private Button continueButtun , backButtun;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_login);

        continueButtun = (Button) findViewById(R.id.btn_continue);
         backButtun = (Button) findViewById(R.id.btn_back);
         continueButtun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AnimationLoginActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        backButtun.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //clear all activity
             finishAffinity();
             //Exit
                System.exit(0);
            }
        });
    }
}