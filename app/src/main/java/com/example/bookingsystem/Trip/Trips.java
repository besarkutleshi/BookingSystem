package com.example.bookingsystem.Trip;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import android.view.MenuItem;
import com.example.bookingsystem.Favorites.FavoritesFragment;
import com.example.bookingsystem.Fragments.ItemFragment;
import com.example.bookingsystem.R;
import com.example.bookingsystem.SettingsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Trips extends AppCompatActivity {
    private BottomNavigationView bottomNavigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trips);
        bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(nav);
        bottomNavigation.getMenu().findItem(R.id.navigation_home).setChecked(true);
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
                        openFragment(FavoritesFragment.NewInstance());
                    return  true;
                case R.id.navigation_home:
                    openFragment(ItemFragment.newInstance());
                    return  true;
                case R.id.navigation_change:
                    openFragment(SettingsFragment.newInstance());
                    return  true;
            }
            return false;
        }
    };
}


