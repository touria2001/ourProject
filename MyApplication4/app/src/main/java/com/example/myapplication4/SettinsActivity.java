package com.example.myapplication4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SettinsActivity extends AppCompatActivity {
    private TextView closeTxtView ;
    private TextView updateTxtView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settins);
         closeTxtView = (TextView) findViewById(R.id.close_settings_btn);
         updateTxtView = (TextView) findViewById(R.id.update_account_settings_btn);
        /* closeTxtView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettinsActivity.this,HomeActivity.class);
                startActivity(intent);
            }
        });*/
    }
}