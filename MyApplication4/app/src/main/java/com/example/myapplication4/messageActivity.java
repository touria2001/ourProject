package com.example.myapplication4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplication4.ViewHolder.MessageViewHolder;
import com.example.myapplication4.ViewHolder.ProductViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import Model.Products;
import Model.message;
import Prevalent.Prevalent;

public class messageActivity extends AppCompatActivity {
    private DatabaseReference MessagesRef;
private String ourUserActuel;
    TextView ourUser;

    DatabaseReference reference;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        MessagesRef = FirebaseDatabase.getInstance().getReference().child("Users");

        recyclerView = findViewById(R.id.recycler_menuChat);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

      /*  messageRecu=(TextView) findViewById(R.id.messageRecu);

        reference= FirebaseDatabase.getInstance().getReference("Users").child(getIntent().getExtras().get("loggedUser").toString()).child("message");
//String message=reference.getValue(String.class);
//        messageRecu.setText(message );*/

     /*   reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String message=snapshot.getValue(String.class);
                messageRecu.setText(message);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/
    }
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerOptions<message> options =
                new FirebaseRecyclerOptions.Builder<message>()
                        .setQuery(MessagesRef, message.class)
                        .build();
        FirebaseRecyclerAdapter<message, MessageViewHolder> adapter =
                new FirebaseRecyclerAdapter<message, MessageViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull MessageViewHolder holder, int position, @NonNull message model) {
                        if( !model.getPhone().equals(getIntent().getExtras().get("loggedUser").toString())){
                        holder.ourUser.setText(model.getPhone());
                        ourUserActuel = model.getPhone();
                        }
                        else {
                            holder.ourUser.setVisibility(View.GONE);
                        }

                    }

                    @NonNull
                    @Override
                    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat, parent, false);
                        MessageViewHolder holder = new MessageViewHolder(view);
                        return holder;
                    }};


        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }
    public void chater(View view) {
        TextView b = (TextView) view;
        String Text = b.getText().toString();
        Intent intent = new Intent(messageActivity.this, whatsapp.class);
        intent.putExtra("loggedUser", Prevalent.currentOnLineUser.getPhone());
        intent.putExtra("ourUserActuel",Text);


        startActivity(intent);
    }


}