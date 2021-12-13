package com.example.myapplication4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class messageActivity extends AppCompatActivity {

    TextView messageRecu;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        messageRecu=(TextView) findViewById(R.id.messageRecu);

        reference= FirebaseDatabase.getInstance().getReference("Users").child(getIntent().getExtras().get("loggedUser").toString()).child("message");
//String message=reference.getValue(String.class);
//        messageRecu.setText(message );

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String message=snapshot.getValue(String.class);
                messageRecu.setText(message);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void repondre(View view) {
    }

}