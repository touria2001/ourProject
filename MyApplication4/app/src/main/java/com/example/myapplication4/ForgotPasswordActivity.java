package com.example.myapplication4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import Model.Users;
import Prevalent.Prevalent;
import io.paperdb.Paper;

public class ForgotPasswordActivity extends AppCompatActivity {
    private Button SendButton;
    private EditText InputPhone;
    private  TextView passwordTextView ;
    private ProgressDialog LoadingBar;
    private String parentDbName = "Users";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        SendButton = (Button) findViewById(R.id.send_btn);
        InputPhone  = (EditText) findViewById(R.id.Login_phone_number_fogot_password);
        TextView passwordTextView = findViewById(R.id.password_link);
        LoadingBar = new ProgressDialog(this);

        SendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VerifierUser();

            }
        });
    }
    private void VerifierUser() {
        String phone = InputPhone.getText().toString();
        if(TextUtils.isEmpty(phone))
        {
            Toast.makeText(this,"Plaese write your phone number...",Toast.LENGTH_SHORT).show();
        }
        else {

            LoadingBar.setMessage("Please wait, while we are cheking the credentials.");
            LoadingBar.setCanceledOnTouchOutside(false);
            LoadingBar.show();
            AllowAccessToAccount(phone);
        }
    }

    private void AllowAccessToAccount(final String phone) {
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();
        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.child(parentDbName).child(phone).exists())
                {
                    Users usersData = snapshot.child(parentDbName).child(phone).getValue(Users.class);
                    if( usersData != null){
                        if(usersData.getPhone().equals(phone)){
                               if(parentDbName.equals("Users"))
                                {
                                    Toast.makeText(ForgotPasswordActivity.this,"Your password will be sent to your Phone number, Check your message box after some time ...",Toast.LENGTH_LONG).show();
                                    //passwordTextView.setText(Prevalent.currentOnLineUser.getPassword());
                                    LoadingBar.dismiss();
                                    Intent intent = new Intent(ForgotPasswordActivity.this,LoginActivity.class);
                                    Prevalent.currentOnLineUser = usersData;
                                    //passwordTextView.setText(Prevalent.currentOnLineUser.getName());
                                    startActivity(intent);
                                }

                        }
                    }
                }
                else
                {
                    Toast.makeText(ForgotPasswordActivity.this,"Account with this "+phone +" number do not exists.",Toast.LENGTH_SHORT).show();
                    LoadingBar.dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

}