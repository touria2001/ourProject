package com.example.myapplication4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ChatActivity extends AppCompatActivity {
    EditText messageSend;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        messageSend=(EditText) findViewById(R.id.messagesend);
        // getIntent().getExtras().get("loggedUser").toString();
        messageSend.setText(getIntent().getExtras().get("destinataire").toString());
//        reference= FirebaseDatabase.getInstance().getReference("Users");
//        reference.child(getIntent().getExtras().get("loggedUser").toString()).child("message").setValue(messageSend.getText());


    }
//    @Override
//    protected void onStart() {
//        super.onStart();
////        reference= FirebaseDatabase.getInstance().getReference("Users");
////       reference.child(getIntent().getExtras().get("loggedUser").toString()).child("message").setValue(messageSend.getText());
//
//    }

    public void chat(View view) {
        reference= FirebaseDatabase.getInstance().getReference("Users");

        reference.child(getIntent().getExtras().get("destinataire").toString()).child("message").setValue(messageSend.getText().toString());

    }
}