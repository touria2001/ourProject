package com.example.myapplication4;

import static android.view.Gravity.CENTER_HORIZONTAL;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication4.ViewHolder.MessageUserViewHolder;
import com.example.myapplication4.ViewHolder.MessageViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

import Model.MessageUser;
import Model.message;

public class whatsapp extends AppCompatActivity {
    private String ourMsg,ourMsg2;
    private DatabaseReference MessagesRef;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    private TextView ourText,ourText2;
    EditText messageSend;
    DatabaseReference reference;
    private String saveCurrentDate, saveCurrentTime,productRandomKey;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whatsapp);
         ourText = (TextView) findViewById(R.id.ourMsg);
        ourText2 = (TextView) findViewById(R.id.ourMsg2);
        MessagesRef = (DatabaseReference) FirebaseDatabase.getInstance().getReference().child("messages");
        recyclerView = findViewById(R.id.recycler_menuWhatsapp);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        messageSend=(EditText) findViewById(R.id.messagesend);
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
                        if(model.getDestinataire().equals(getIntent().getExtras().get("ourUserActuel").toString()) && model.getUser().equals(getIntent().getExtras().get("loggedUser").toString()))
                        {
                            holder.ourMsg.setText(model.getMessage());
                        } else if (model.getDestinataire().equals(getIntent().getExtras().get("loggedUser").toString()) && model.getUser().equals(getIntent().getExtras().get("ourUserActuel").toString())){
                            holder.ourMsg2.setText(model.getMessage());
                            holder.ourMsg2.setVisibility(View.VISIBLE);
                            holder.ourMsg.setVisibility(View.GONE);
                            // holder.ourMsg.setBackgroundResource(R.drawable.message);

                        }else {
                            holder.ourMsg.setVisibility(View.GONE);
                        }  }

                    @NonNull
                    @Override
                    public MessageUserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_discusion, parent, false);
                        MessageUserViewHolder holder = new MessageUserViewHolder(view);
                        return holder;
                    }};


        recyclerView.setAdapter(adapter);
        adapter.startListening();


    }

    public void chat(View view) {
        reference= FirebaseDatabase.getInstance().getReference("messages");
                Calendar calendar = Calendar.getInstance();


                SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
                saveCurrentDate = currentDate.format(calendar.getTime());
                SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
                saveCurrentTime = currentTime.format(calendar.getTime());
               String m=saveCurrentDate + saveCurrentTime;
                productRandomKey = m.replace(".",",");
               HashMap<String, Object> productMap = new HashMap<>();
                productMap.put("message", messageSend.getText().toString());
                productMap.put("user", getIntent().getExtras().get("loggedUser").toString());
                productMap.put("destinataire", getIntent().getExtras().get("ourUserActuel").toString());

                reference.child(productRandomKey).updateChildren(productMap);
        EditText t=(EditText)findViewById(R.id.messagesend) ;
        t.setText("");




    }
}
