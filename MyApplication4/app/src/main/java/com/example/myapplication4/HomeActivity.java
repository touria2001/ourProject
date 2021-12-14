package com.example.myapplication4;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication4.ViewHolder.ProductViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication4.databinding.ActivityHomeBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

import Model.Products;
import Prevalent.Prevalent;
import de.hdodenhof.circleimageview.CircleImageView;
import io.paperdb.Paper;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private DatabaseReference ProductsRef;
    private AppBarConfiguration mAppBarConfiguration;
    private ActivityHomeBinding binding;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    String destinataire;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Paper.init(this);


        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.appBarHome.toolbar.setTitle("Home");

        setSupportActionBar(binding.appBarHome.toolbar);
        ProductsRef = FirebaseDatabase.getInstance().getReference().child("Products");
//        binding.appBarHome.fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

       DrawerLayout drawer = binding.drawerLayout;
      NavigationView navigationView = binding.navView;
        navigationView.setNavigationItemSelectedListener(this);

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_home);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        View headerView = navigationView.getHeaderView(0);
        TextView userNameTextView = headerView.findViewById(R.id.user_profile_name);
        CircleImageView profileImageView = headerView.findViewById(R.id.user_profile_image);
        userNameTextView.setText(Prevalent.currentOnLineUser.getName());
        Picasso.get().load(Prevalent.currentOnLineUser.getImage()).placeholder(R.drawable.profile).into(profileImageView);

        recyclerView = findViewById(R.id.recycler_menu);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }
Button reserver;
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerOptions<Products> options =
                new FirebaseRecyclerOptions.Builder<Products>()
                .setQuery(ProductsRef, Products.class)
                .build();
        FirebaseRecyclerAdapter<Products, ProductViewHolder> adapter =
                new FirebaseRecyclerAdapter<Products, ProductViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull ProductViewHolder holder, int position, @NonNull Products model) {
                        holder.txtProductPrice.setText("contacter : "+model.getUser());
                        destinataire=model.getUser();
                       // holder.txtProductName.setText(model.getPname());
                        holder.txtProductDescription.setText("ville : "+model.getDescription());

                        holder.pid = model.getPid();
                        if (!getIntent().getExtras().get("loggedUser").toString().equals(model.getUser())){
                            holder.deleteBtn.setVisibility(View.GONE);
                        } else {
                            holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    AlertDialog.Builder alert = new AlertDialog.Builder(HomeActivity.this);
                                    alert.setTitle("Confirmation");
                                    alert.setMessage("Are you sure you want to delete this item ?");
                                    alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            ProductsRef.child(holder.pid).removeValue();
                                            dialogInterface.dismiss();
                                        }
                                    });
                                    alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            dialogInterface.dismiss();
                                        }
                                    });
                                    alert.show();

                                }
                            });
                        }
                        holder.reserver.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Map<String, Object> updateReservation = new HashMap<String,Object>();
                                if(model.getReserver().equals("non")){
                                    updateReservation.put("reserver", getIntent().getExtras().get("loggedUser").toString());
                                    ProductsRef.child(holder.pid).updateChildren(updateReservation);
                                }
                                else{
                                    if(model.getReserver().equals(getIntent().getExtras().get("loggedUser").toString()))  {

                                        updateReservation.put("reserver", "non");
                                        ProductsRef.child(holder.pid).updateChildren(updateReservation);

                                    }}
                            }
                        });
                        if(model.getReserver().equals("non")){
                            holder.reserver.setBackgroundResource(R.color.gris);
                        }
                        else{
                            holder.reserver.setBackgroundResource(R.color.green2);
                            if(model.getReserver().equals(getIntent().getExtras().get("loggedUser").toString())){
                                holder.reserver.setText("annuler");}
                            else{
                                holder.reserver.setText("reserved");
                            }
                        }

                        Picasso.get().load(model.getImage()).into(holder.imageView);

//                        if(model.getReserver().equals("non")){
//                            holder.reserver.setBackgroundResource(R.color.white);
//                        }
//                        else { holder.reserver.setBackgroundResource(R.color.black);}




                    }

                    @NonNull
                    @Override
                    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_items_layout, parent, false);
                        ProductViewHolder holder = new ProductViewHolder(view);
                        return holder;
                    }
                };

recyclerView.setAdapter(adapter);
adapter.startListening();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    //ajouter

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_home);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();

//        if (id == R.id.action_settings)
//        {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {
        Log.d("myTag", "This is my messageeeeeeeeeeeeeeeeeee");

        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.contact_admin)
        {
            Intent intent = new Intent(this, messageActivity.class);
            intent.putExtra("loggedUser", getIntent().getExtras().get("loggedUser").toString());

            startActivity(intent);
        }
        else if (id == R.id.nav_settings)
        {

            Intent intent = new Intent(HomeActivity.this, SettinsActivity.class);
            intent.putExtra("loggedUser", Prevalent.currentOnLineUser.getPhone());
            startActivity(intent);
        }
        else if (id == R.id.nav_about_as)
        {

            Intent intent = new Intent(HomeActivity.this, AboutAsActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.contact_as)
        {

            Intent intent = new Intent(HomeActivity.this, BotChatActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.nav_logout)
        {
            Paper.book().destroy();

            Intent intent = new Intent(HomeActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void ajouter(View view) {
        Intent intent = new Intent(this, AdminCategoryActivity.class);
        intent.putExtra("loggedUser", getIntent().getExtras().get("loggedUser").toString());
        startActivity(intent);
    }

    public void parler(View view){
        Intent intent = new Intent(this, ChatActivity.class);
        intent.putExtra("loggedUser", getIntent().getExtras().get("loggedUser").toString());
        intent.putExtra("destinataire",destinataire );
        startActivity(intent);
    }

}
