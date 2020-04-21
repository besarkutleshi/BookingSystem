package com.example.bookingsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.service.media.MediaBrowserService;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class BookTrip extends AppCompatActivity {
    BottomNavigationView bottomNavigation;
    Spinner spinner;
    ImageView img;
    TextView txtdesc;
    TextView txtPrice;
    String desc;
    double price;
    String url;
    List<Integer> FreeSeats;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_trip);
        img = findViewById(R.id.imgTrip);
        txtdesc = findViewById(R.id.txtdescription);
        txtPrice = findViewById(R.id.txtprice);
        spinner = findViewById(R.id.spinner);
        bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(nav);
        bottomNavigation.getMenu().findItem(R.id.navigation_home).setChecked(true);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.chairs, R.layout.spinner_item);
        spinner.setAdapter(adapter);
        GetData();
        SetData();
    }

    private void GetData(){
        if(getIntent().hasExtra("Description") && getIntent().hasExtra("Price") && getIntent().hasExtra("Photo")){
            desc = getIntent().getStringExtra("Description");
            price = getIntent().getDoubleExtra("Price",0);
            url = getIntent().getStringExtra("Photo");
            int id = getIntent().getIntExtra("ID",0);
            PasData(id);
        }else{
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }
    }


    void SetData(){
        txtPrice.setText("Price : " + Double.toString(price));
        txtdesc.setText(desc);
        Picasso.get().load(url).resize(1040,498).into(img);
    }

    BottomNavigationView.OnNavigationItemSelectedListener nav = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.navigation_favorites:
                    startActivity(new Intent(BookTrip.this,Favorites.class));
                    return  true;
                case R.id.navigation_home:
                    startActivity(new Intent(BookTrip.this,Trips.class));
                    return true;
                case R.id.navigation_change:
                    startActivity(new Intent(BookTrip.this,ChangePassword.class));
                    return true;
                case R.id.navigation_logout:
                    HelperClass.Logout();
                    startActivity(new Intent(BookTrip.this,MainActivity.class));
                    return  true;
            }
            return false;
        }
    };

    public void PasData(final int id){
        Retrofit retrofit = HelperClass.GetRetrofit();
        ITripAPI tripAPI = retrofit.create(ITripAPI.class);
        Call<List<Integer>> call = tripAPI.ListChairs(id);
        call.enqueue(new Callback<List<Integer>>() {
            @Override
            public void onResponse(Call<List<Integer>> call, Response<List<Integer>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(BookTrip.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }else{
                    List<Integer> chairs = new ArrayList<>();
                    chairs = response.body();
                    ArrayList<Integer> FreeSeats = FreeChairs(chairs);
                    ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<Integer>(
                            BookTrip.this,
                            R.layout.spinner_item,
                            FreeSeats
                    );
                    spinner.setAdapter(arrayAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Integer>> call, Throwable t) {
                Toast.makeText(BookTrip.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public ArrayList<Integer> FreeChairs(List<Integer> reservedseates){
        ArrayList<Integer> FreeChairs = new ArrayList<>();
        int no = 1;
        for (int i = 0; i <= 10; i++){
            if(i >= reservedseates.size()){
                FreeChairs.add(no);
                no++;
                continue;
            }
            if(reservedseates.get(i) != no ){
                FreeChairs.add(no);
                no++;
                continue;
            }
            else{
                no++;
            }
        }
        return FreeChairs;
    }
}
