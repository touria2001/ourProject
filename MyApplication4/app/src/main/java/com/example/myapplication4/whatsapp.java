package com.example.myapplication4;

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
    private String ourMsg;
    private DatabaseReference MessagesRef;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    private TextView ourText;
    EditText messageSend;
    DatabaseReference reference;
    private String saveCurrentDate, saveCurrentTime,productRandomKey;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whatsapp);
         ourText = (TextView) findViewById(R.id.ourMsg);
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
                            holder.ourMsg.setText(model.getMessage());
                            holder.ourMsg.setGravity(Gravity.RIGHT);
                        }else {
                            holder.ourMsg.setVisibility(View.GONE);
                        }








                    }

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
//    public void chat(View view) {
//        Intent intent = new Intent(this, HomeActivity.class);
//        intent.putExtra("loggedUser", getIntent().getExtras().get("loggedUser").toString());
//        startActivity(intent);
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
               //Log.d("jjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj",productRandomKey);
                HashMap<String, Object> productMap = new HashMap<>();
                productMap.put("message", messageSend.getText().toString());
                productMap.put("user", getIntent().getExtras().get("loggedUser").toString());
                productMap.put("destinataire", getIntent().getExtras().get("ourUserActuel").toString());

                reference.child(productRandomKey).updateChildren(productMap);
                Toast.makeText(whatsapp.this, "Message has been sent", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(whatsapp.this, HomeActivity.class);
           intent.putExtra("loggedUser", getIntent().getExtras().get("loggedUser").toString());
        startActivity(intent);
            }
        });
        alert.setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                Toast.makeText(whatsapp.this, "Messge was not sent", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(whatsapp.this, HomeActivity.class);
               intent.putExtra("loggedUser", getIntent().getExtras().get("loggedUser").toString());
            startActivity(intent);

            }
        });

        alert.create().show();



    }
}
   // EditText messageSend;
//    DatabaseReference reference;
//    private String saveCurrentDate, saveCurrentTime,productRandomKey;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_chat);
//        messageSend=(EditText) findViewById(R.id.messagesend);
//
//    }
//
//    public void chat(View view) {
//        AlertDialog.Builder alert =new AlertDialog.Builder(this);
//        alert.setMessage("do you want to send the message? ");
//        alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int i) {
//                reference= FirebaseDatabase.getInstance().getReference("messages");
//                Calendar calendar = Calendar.getInstance();
//
//                SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
//                saveCurrentDate = currentDate.format(calendar.getTime());
//
//                SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
//                saveCurrentTime = currentTime.format(calendar.getTime());
//                // myStr.replace('l', 'p')
//                String m=saveCurrentDate + saveCurrentTime;
//                productRandomKey = m.replace(".",",");
//                Log.d("jjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj",productRandomKey);
//                HashMap<String, Object> productMap = new HashMap<>();
//                productMap.put("message", messageSend.getText().toString());
//                productMap.put("user", getIntent().getExtras().get("loggedUser").toString());
//                productMap.put("destinataire", getIntent().getExtras().get("destinataire").toString());
//
//                reference.child(productRandomKey).updateChildren(productMap);
//                Toast.makeText(ChatActivity.this, "Message has been sent", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(ChatActivity.this,HomeActivity.class);
//                intent.putExtra("loggedUser", getIntent().getExtras().get("loggedUser").toString());
//                startActivity(intent);
//            }
//        });
//        alert.setNegativeButton("no", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int i) {
//                Toast.makeText(ChatActivity.this, "Messge was not sent", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(ChatActivity.this, HomeActivity.class);
//                intent.putExtra("loggedUser", getIntent().getExtras().get("loggedUser").toString());
//
//                startActivity(intent);
//
//            }
//        });
//
//        alert.create().show();
//
//
//
  //  }