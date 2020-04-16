package com.example.bookingsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Booking extends AppCompatActivity {
    Button btnFavoritesss;
    BottomNavigationView bottomNavigation;
    Intent favorites;
    Intent password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        favorites = new Intent(this, Favorites.class);
        password = new Intent(this, ChangePassword.class);
        btnFavoritesss = findViewById(R.id.btnfav);
        bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(nav);
        btnFavoritesss.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(Booking.this, "Saved", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    BottomNavigationView.OnNavigationItemSelectedListener nav = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.navigation_favorites:
                    startActivity(favorites);
                    return  true;
                case R.id.navigation_change:
                    startActivity(password);
                    return true;
            }
            return false;
        }
    };
}
