package com.example.myapplication4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.LoginFilter;
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
import com.rey.material.widget.CheckBox;

import Model.Users;
import Prevalent.Prevalent;
import io.paperdb.Paper;

public class LoginActivity extends AppCompatActivity {
    private EditText InputPhoneNumber ,InputPassword;
    private Button LoginButton;
    private ProgressDialog LoadingBar;
    // private TextView AdminLink,NotAdminLink;
    private TextView NotAdminLink;
    private String parentDbName = "Users";
    private CheckBox checkBoxRemrmberMe;
    private TextView forgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        LoginButton = (Button) findViewById(R.id.login_btn);
        forgotPassword = (TextView) findViewById(R.id.forget_password_link);
        InputPhoneNumber = (EditText) findViewById(R.id.Login_phone_number_input);
        InputPassword  = (EditText) findViewById(R.id.Login_password_input);
        LoadingBar = new ProgressDialog(this);
//        AdminLink =(TextView) findViewById(R.id.admin_panel_link) ;
        NotAdminLink =(TextView) findViewById(R.id.not_admin_panel_link) ;
        LoadingBar = new ProgressDialog(this);
        checkBoxRemrmberMe = (CheckBox) findViewById(R.id.remember_me_chkb);
        Paper.init(this);

        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginUser();

            }
        });
            forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });


//        AdminLink.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                LoginButton.setText("Login Admin");
////               AdminLink.setVisibility(View.INVISIBLE);
////                NotAdminLink.setVisibility(View.VISIBLE);
//                parentDbName = "Admins";
//            }
//        });
        NotAdminLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginButton.setText("Login ");
                //  AdminLink.setVisibility(View.VISIBLE);
                NotAdminLink.setVisibility(View.INVISIBLE);
                parentDbName = "Users";

            }
        });
    }

    private void LoginUser() {
        String password = InputPassword.getText().toString();
        String phone = InputPhoneNumber.getText().toString();

        if(TextUtils.isEmpty(phone))
        {
            Toast.makeText(this,"Plaese write your phone number...",Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(password))
        {
            Toast.makeText(this,"Plaese write your password...",Toast.LENGTH_SHORT).show();
        }
        else {
            LoadingBar.setTitle("Login Account");
            LoadingBar.setMessage("Please wait, while we are cheking the credentials.");
            LoadingBar.setCanceledOnTouchOutside(false);
            LoadingBar.show();
            AllowAccessToAccount(phone,password);
        }
    }
    private void AllowAccessToAccount(final String phone,final String password) {
        if(checkBoxRemrmberMe.isChecked())
        {
            Paper.book().write(Prevalent.UserPhoneKey,phone);
            Paper.book().write(Prevalent.UserPasswordKey,password);
        }
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
                            if(usersData.getPassword().equals(password)){
                                if(parentDbName.equals("Admins"))
                                {
                                    Toast.makeText(LoginActivity.this,"Welcome Admin,you are Logged in Successfuly...",Toast.LENGTH_SHORT).show();
                                    LoadingBar.dismiss();
                                    Intent intent = new Intent(LoginActivity.this,AdminCategoryActivity.class);
                                    startActivity(intent);
                                }
                                else if(parentDbName.equals("Users"))
                                {
                                    Toast.makeText(LoginActivity.this,"Logged in Successfuly...",Toast.LENGTH_SHORT).show();
                                    LoadingBar.dismiss();
                                    Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
                                    Prevalent.currentOnLineUser = usersData;
                                    startActivity(intent);
                                }
                            }
                            else
                            {
                                LoadingBar.dismiss();
                                Toast.makeText(LoginActivity.this,"Password is incorrect.",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
                else
                {
                    Toast.makeText(LoginActivity.this,"Account with this "+phone +" number do not exists.",Toast.LENGTH_SHORT).show();
                    LoadingBar.dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }















}