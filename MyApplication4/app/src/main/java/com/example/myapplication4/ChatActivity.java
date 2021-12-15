package com.example.myapplication4;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class ChatActivity extends AppCompatActivity {
    EditText messageSend;
    DatabaseReference reference;
    private String saveCurrentDate, saveCurrentTime,productRandomKey;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        messageSend=(EditText) findViewById(R.id.messagesend);
      //  messageSend.setText(getIntent().getExtras().get("destinataire").toString());
        // getIntent().getExtras().get("loggedUser").toString();
     //   messageSend.setText(getIntent().getExtras().get("destinataire").toString());
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
               reference= FirebaseDatabase.getInstance().getReference("messages");
                Calendar calendar = Calendar.getInstance();

                SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
                saveCurrentDate = currentDate.format(calendar.getTime());

                SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
                saveCurrentTime = currentTime.format(calendar.getTime());
               // myStr.replace('l', 'p')
                        String m=saveCurrentDate + saveCurrentTime;
                productRandomKey = m.replace(".",",");
             Log.d("jjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj",productRandomKey);
            //  reference.child(getIntent().getExtras().get("destinataire").toString()).child("messages").child("hh").child("message").setValue(messageSend.getText().toString());
//                reference.child(getIntent().getExtras().get("destinataire").toString()).child("messages").child( "hh").child("user").setValue(getIntent().getExtras().get("loggedUser").toString());
//                reference.child(getIntent().getExtras().get("destinataire").toString()).child("messages").child( "hh").child("destinataire").setValue(getIntent().getExtras().get("destinataire").toString());

                HashMap<String, Object> productMap = new HashMap<>();
                productMap.put("message", messageSend.getText().toString());
//                productMap.put("date", saveCurrentDate);
//                productMap.put("time", saveCurrentTime);
                productMap.put("user", getIntent().getExtras().get("loggedUser").toString());
                productMap.put("destinataire", getIntent().getExtras().get("destinataire").toString());
                String name=getIntent().getExtras().get("loggedUser").toString()+getIntent().getExtras().get("destinataire").toString();
               reference.child(name).child(productRandomKey).updateChildren(productMap);
                //reference.child(getIntent().getExtras().get("destinataire").toString()).child("messages").child("hhh2").updateChildren(productMap);

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