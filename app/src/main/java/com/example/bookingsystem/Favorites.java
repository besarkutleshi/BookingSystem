package com.example.bookingsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

//import com.example.bookingsystem.Account.ChangePassword;
import com.example.bookingsystem.Trip.Trips;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Favorites extends AppCompatActivity {

    BottomNavigationView bottomNavigation;
    Intent home;
    Intent password;
    Intent main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
        home = new Intent(this, Trips.class);
        password = new Intent(this, MainActivity.class);
        bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(nav);
        bottomNavigation.getMenu().findItem(R.id.navigation_favorites).setChecked(true);
    }


    BottomNavigationView.OnNavigationItemSelectedListener nav = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.navigation_home:
                    startActivity(home);
                    return true;
                case R.id.navigation_change:
                    startActivity(password);
                    return true;
                case R.id.navigation_logout:
                    HelperClass.Logout();
                    startActivity(main);
            }
            return false;
        }
    };
}
