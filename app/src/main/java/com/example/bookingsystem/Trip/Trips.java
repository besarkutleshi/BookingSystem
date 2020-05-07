package com.example.bookingsystem.Trip;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.bookingsystem.Account.ChangePassword;
import com.example.bookingsystem.Favorites;
import com.example.bookingsystem.HelperClass;
import com.example.bookingsystem.Interface.ITripAPI;
import com.example.bookingsystem.MainActivity;
import com.example.bookingsystem.Adapter.MyAdapter;
import com.example.bookingsystem.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Trips extends AppCompatActivity {
    ITripAPI tripAPI;
    RecyclerView recyclerView;
    BottomNavigationView bottomNavigation;
    Intent favorites;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trips);
        favorites = new Intent(this, Favorites.class);
        recyclerView = findViewById(R.id.recyclerTrips);
        tripAPI = HelperClass.GetRetrofit().create(ITripAPI.class);
        bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(nav);
        bottomNavigation.getMenu().findItem(R.id.navigation_home).setChecked(true);
        ShowTrips();

    }

    BottomNavigationView.OnNavigationItemSelectedListener nav = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.navigation_favorites:
                        startActivity(new Intent(Trips.this,Favorites.class));
                    return  true;
                case R.id.navigation_home:
                    startActivity(new Intent(Trips.this,Trips.class));
                    return  true;
                case R.id.navigation_change:
                    startActivity(new Intent(Trips.this, ChangePassword.class));
                    return  true;
                case R.id.navigation_logout:
                    HelperClass.Logout();
                    startActivity(new Intent(Trips.this, MainActivity.class));
                    return  true;
            }
            return false;
        }
    };

    public void ShowTrips(){
        Call<List<Trip>> call = tripAPI.Trips();
        call.enqueue(new Callback<List<Trip>>() {
            @Override
            public void onResponse(Call<List<Trip>> call, Response<List<Trip>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(Trips.this, response.message(), Toast.LENGTH_SHORT).show();
                }
                else{
                    List<Trip> Trips = response.body();
                    MyAdapter adapter = new MyAdapter(Trips.this,Trips);
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(Trips.this));
                }
            }

            @Override
            public void onFailure(Call<List<Trip>> call, Throwable t) {
                Toast.makeText(Trips.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}


