package com.example.myapplication4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplication4.ViewHolder.MessageUserViewHolder;
import com.example.myapplication4.ViewHolder.MessageViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import Model.MessageUser;
import Model.message;

public class whatsapp extends AppCompatActivity {
    private String ourMsg;
    private DatabaseReference MessagesRef;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whatsapp);
        MessagesRef = FirebaseDatabase.getInstance().getReference().child("messages");
        recyclerView = findViewById(R.id.recycler_menuWhatsapp);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerOptions<MessageUser> options =
                new FirebaseRecyclerOptions.Builder<MessageUser>()
                        .setQuery(MessagesRef, MessageUser.class)
                        .build();
        FirebaseRecyclerAdapter<MessageUser, MessageUserViewHolder> adapter =
                new FirebaseRecyclerAdapter<MessageUser, MessageUserViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull MessageUserViewHolder holder, int position, @NonNull MessageUser model) {

                        holder.ourMsg.setText("model.getMessage()");


                    }

                    @NonNull
                    @Override
                    public MessageUserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat, parent, false);
                        MessageUserViewHolder holder = new MessageUserViewHolder(view);
                        return holder;
                    }};


        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }
}