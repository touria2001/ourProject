package com.example.myapplication4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class AdminCategoryActivity extends AppCompatActivity {
    private ImageView tShirts, sportsTShirts, femaleDresses, sweathers;
    private ImageView glasses, hatsCaps, walletsBagsPurses, shoes;
    private ImageView headPhonesHandFree, Laptops, watches, mobilPhones;
   // private Button cancelBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_category);

        tShirts = (ImageView) findViewById(R.id.t_shirts);
        sportsTShirts=(ImageView)findViewById(R.id.sports_t_shirts);
        femaleDresses=(ImageView)findViewById(R.id.female_dresses);
        sweathers= (ImageView)findViewById(R.id.sweathers);
        glasses = (ImageView) findViewById(R.id.glasses);
        hatsCaps=(ImageView)findViewById(R.id.halts_caps);
        walletsBagsPurses=(ImageView)findViewById(R.id.purses_bags_wallets);

        shoes = (ImageView) findViewById(R.id.shoess);
        headPhonesHandFree=(ImageView)findViewById(R.id.headphones_handfree);
        Laptops=(ImageView)findViewById(R.id.laptop_pc);

        watches = (ImageView) findViewById(R.id.watches);
        mobilPhones=(ImageView)findViewById(R.id.mobilesphones);

       /* cancelBtn =(Button) findViewById(R.id.btn_close) ;

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this,HomeActivity.class);
                startActivity(intent);
            }
        });*/

        tShirts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this,AdminAddNewProductActivity.class);
                intent.putExtra("loggedUser", getIntent().getExtras().get("loggedUser").toString());
                intent.putExtra("category","tShirts");
                startActivity(intent);

            }
        });
        sportsTShirts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this,AdminAddNewProductActivity.class);
                intent.putExtra("loggedUser", getIntent().getExtras().get("loggedUser").toString());
                intent.putExtra("category","Sports tShirts");
                startActivity(intent);
            }
        });

        femaleDresses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this,AdminAddNewProductActivity.class);
                intent.putExtra("loggedUser", getIntent().getExtras().get("loggedUser").toString());
                intent.putExtra("category","Female Dresses");
                startActivity(intent);
            }
        });

        sweathers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this,AdminAddNewProductActivity.class);
                intent.putExtra("loggedUser", getIntent().getExtras().get("loggedUser").toString());
                intent.putExtra("category","Sweathers");
                startActivity(intent);
            }
        });

        glasses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this,AdminAddNewProductActivity.class);
                intent.putExtra("loggedUser", getIntent().getExtras().get("loggedUser").toString());
                intent.putExtra("category","Glasses");
                startActivity(intent);
            }
        });

        hatsCaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this,AdminAddNewProductActivity.class);
                intent.putExtra("loggedUser", getIntent().getExtras().get("loggedUser").toString());
                intent.putExtra("category","Hats Caps");
                startActivity(intent);
            }
        });

        walletsBagsPurses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this,AdminAddNewProductActivity.class);
                intent.putExtra("loggedUser", getIntent().getExtras().get("loggedUser").toString());
                intent.putExtra("category","Wallets Bags Purses");
                startActivity(intent);
            }
        });

        shoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this,AdminAddNewProductActivity.class);
                intent.putExtra("loggedUser", getIntent().getExtras().get("loggedUser").toString());
                intent.putExtra("category","Shoes");
                startActivity(intent);
            }
        });

        headPhonesHandFree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this,AdminAddNewProductActivity.class);
                intent.putExtra("loggedUser", getIntent().getExtras().get("loggedUser").toString());
                intent.putExtra("category","HeadPhones HandFree");
                startActivity(intent);
            }
        });

        Laptops.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this,AdminAddNewProductActivity.class);
                intent.putExtra("loggedUser", getIntent().getExtras().get("loggedUser").toString());
                intent.putExtra("category","Laptops");
                startActivity(intent);
            }
        });

        watches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this,AdminAddNewProductActivity.class);
                intent.putExtra("loggedUser", getIntent().getExtras().get("loggedUser").toString());
                intent.putExtra("category","Watches");
                startActivity(intent);
            }
        });

        mobilPhones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this,AdminAddNewProductActivity.class);
                intent.putExtra("loggedUser", getIntent().getExtras().get("loggedUser").toString());
                intent.putExtra("category","Mobile Phones");
                startActivity(intent);
            }
        });
    }
}