package com.example.myapplication4;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

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
        AlertDialog.Builder alert =new AlertDialog.Builder(this);
        alert.setMessage("do you want to send the message? ");
        alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                reference= FirebaseDatabase.getInstance().getReference("Users");

                reference.child(getIntent().getExtras().get("destinataire").toString()).child("message").setValue(messageSend.getText().toString());

                Toast.makeText(ChatActivity.this, "Message has been sent", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ChatActivity.this,HomeActivity.class);
                intent.putExtra("loggedUser", getIntent().getExtras().get("loggedUser").toString());
                startActivity(intent);
          }
        });
        alert.setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                Toast.makeText(ChatActivity.this, "Messge was not sent", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ChatActivity.this, HomeActivity.class);
                intent.putExtra("loggedUser", getIntent().getExtras().get("loggedUser").toString());

                startActivity(intent);

            }
        });

        alert.create().show();



    }
}