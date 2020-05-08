package com.example.bookingsystem.Trip;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.bookingsystem.Account.LogoutFragment;
import com.example.bookingsystem.Favorites;
import com.example.bookingsystem.Account.ChangePasswordFragment;
import com.example.bookingsystem.Fragments.ItemFragment;
import com.example.bookingsystem.HelperClass;
import com.example.bookingsystem.Interface.ITripAPI;
import com.example.bookingsystem.MainActivity;
import com.example.bookingsystem.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Trips extends AppCompatActivity {
    private ITripAPI tripAPI;
    private RecyclerView recyclerView;
    private BottomNavigationView bottomNavigation;
    private Intent favorites;
    private TripRepository _tripRep;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trips);
        favorites = new Intent(this, Favorites.class);
        //recyclerView = findViewById(R.id.recyclerTrips);
        tripAPI = HelperClass.GetRetrofit().create(ITripAPI.class);
        bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(nav);
        bottomNavigation.getMenu().findItem(R.id.navigation_home).setChecked(true);
        //_tripRep = new TripRepository(Trips.this);
        //_tripRep.ShowTrips(recyclerView);
        ItemFragment item = new ItemFragment();
        openFragment(item);
    }


    public void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    BottomNavigationView.OnNavigationItemSelectedListener nav = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.navigation_favorites:
                        startActivity(new Intent(Trips.this,Favorites.class));
                    return  true;
                case R.id.navigation_home:
                    openFragment(ItemFragment.newInstance());
                    return  true;
                case R.id.navigation_change:
                    openFragment(ChangePasswordFragment.newInstance());
                    return  true;
                case R.id.navigation_logout:
                    openFragment(LogoutFragment.newInstance());
                    return  true;
            }
            return false;
        }
    };
}


