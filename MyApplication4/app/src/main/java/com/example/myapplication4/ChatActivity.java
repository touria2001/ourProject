package com.example.myapplication4;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
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
//        Intent intent = new Intent(this, HomeActivity.class);
//        intent.putExtra("loggedUser", getIntent().getExtras().get("loggedUser").toString());
//        startActivity(intent);

//        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
//        builder1.setMessage("Write your message here.");
//        builder1.setCancelable(true);
//
//        builder1.setPositiveButton(
//                "Yes",
//                new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        dialog.cancel();
//                    }
//                });
//
//        builder1.setNegativeButton(
//                "No",
//                new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        dialog.cancel();
//                    }
//                });
//
//        AlertDialog alert11 = builder1.create();
//        alert11.show();
    }
}