package com.example.bookingsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Favorites extends AppCompatActivity {

    BottomNavigationView bottomNavigation;
    Intent home;
    Intent password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
        home = new Intent(this, Booking.class);
        password = new Intent(this, ChangePassword.class);
        bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(nav);
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
            }
            return false;
        }
    };
}
