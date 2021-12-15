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

    }

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
                 HashMap<String, Object> productMap = new HashMap<>();
                productMap.put("message", messageSend.getText().toString());
                  productMap.put("user", getIntent().getExtras().get("loggedUser").toString());
                productMap.put("destinataire", getIntent().getExtras().get("destinataire").toString());

               reference.child(productRandomKey).updateChildren(productMap);
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