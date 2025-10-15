package com.example.ergasia1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.room.Room;


import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;
    public static SupplyDatabase myAppDatabase;
    public static FirebaseFirestore db;

    //private static final String NOTIFICATION_ID_STRING = "0";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Orders Notification";
            String description = "Notifications about my order";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel channel = new NotificationChannel(NOTIFICATION_ID_STRING, name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }*/


        myAppDatabase = Room.databaseBuilder(getApplicationContext(),SupplyDatabase.class,"supplyDB").allowMainThreadQueries().build();
        db = FirebaseFirestore.getInstance();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav, R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item){
        if(item.getItemId()==R.id.nav_home){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
        } else if (item.getItemId()==R.id.nav_query) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new QueryFragment()).commit();
        } else if(item.getItemId()==R.id.nav_insert_product){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new addProductFragment()).commit();
        }else if(item.getItemId()==R.id.nav_edit_product){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new editProductFragment()).commit();
        }else if(item.getItemId()==R.id.nav_delete_product){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new deleteProductFragment()).commit();
        }else if(item.getItemId()==R.id.nav_insert_company){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new addCompanyFragment()).commit();
        }else if(item.getItemId()==R.id.nav_edit_company){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new editCompanyFragment()).commit();
        }else if(item.getItemId()==R.id.nav_delete_company){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new deleteCompanyFragment()).commit();
        }else if(item.getItemId()==R.id.nav_insert_supply){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new addSupplyFragment()).commit();
        }else if(item.getItemId()==R.id.nav_edit_supply){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new editSupplyFragment()).commit();
        }else if(item.getItemId()==R.id.nav_delete_supply){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new deleteSupplyFragment()).commit();
        }
        else if(item.getItemId()==R.id.nav_insert_customer){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new insertCustomerFragment()).commit();
        }else if(item.getItemId()==R.id.nav_insert_trans){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new insertTransactionFragment()).commit();
        }else if(item.getItemId()==R.id.nav_delete_customer){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new deleteCustomerFragment()).commit();
        }else if(item.getItemId()==R.id.nav_delete_trans){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new deleteTransactionFragment()).commit();
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.isDrawerOpen(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }
}