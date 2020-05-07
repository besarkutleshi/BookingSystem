package com.example.bookingsystem.Booking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookingsystem.Account.ChangePassword;
import com.example.bookingsystem.Favorites;
import com.example.bookingsystem.HelperClass;
import com.example.bookingsystem.Interface.IBooking;
import com.example.bookingsystem.Interface.ITripAPI;
import com.example.bookingsystem.MainActivity;
import com.example.bookingsystem.R;
import com.example.bookingsystem.Trip.Trips;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class BookTrip extends AppCompatActivity {
    private static final DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    BottomNavigationView bottomNavigation;
    Spinner spinner;
    ImageView img;
    TextView txtdesc;TextView txtPrice;
    String desc;double price;String url;
    java.sql.Date date;
    List<Integer> FreeSeats;
    Button btnBook;
    EditText txtClientName;
    EditText txtClientSurname;
    int ChairNo; int TripID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_trip);
        btnBook = findViewById(R.id.btnbook);
        img = findViewById(R.id.imgTrip);
        txtdesc = findViewById(R.id.txtdescription);txtPrice = findViewById(R.id.txtprice);
        spinner = findViewById(R.id.spinner);
        bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(nav);
        bottomNavigation.getMenu().findItem(R.id.navigation_home).setChecked(true);
        txtClientName = findViewById(R.id.txtclientname); txtClientSurname = findViewById(R.id.txtclientSurname);
        GetData();
        SetData();
        PasData(TripID);


        btnBook.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Date date = new Date();
                        String datetime = sdf.format(date).toString();
                        String chairno = spinner.getSelectedItem().toString();
                        Booking model = new Booking(0,datetime,desc,TripID,txtClientName.getText().toString(),txtClientSurname.getText().toString(),
                                MainActivity.email,Integer.parseInt(chairno),price);
                        Book(model);
                    }
                }
        );
    }

    public void Book(Booking model){
        Retrofit retrofit = HelperClass.GetRetrofit();
        IBooking bookingAPI = retrofit.create(IBooking.class);
        Call<Booking> call = bookingAPI.InsertBook(model);
        call.enqueue(new Callback<Booking>() {
            @Override
            public void onResponse(Call<Booking> call, Response<Booking> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(BookTrip.this, "Booking Failed", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(BookTrip.this, "Booking Successful", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Booking> call, Throwable t) {

            }
        });
    }



    private void GetData() {
        if(getIntent().hasExtra("Description") && getIntent().hasExtra("Price") && getIntent().hasExtra("Photo")){
            desc = getIntent().getStringExtra("Description");
            price = getIntent().getDoubleExtra("Price",0);
            url = getIntent().getStringExtra("Photo");
            TripID = getIntent().getIntExtra("ID",0);
            DateFormat format = new SimpleDateFormat("yyyy MM, dd", Locale.ENGLISH);
            String a = getIntent().getStringExtra("Date");
            try {
                date = (java.sql.Date) format.parse(a);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            PasData(TripID);
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
                    startActivity(new Intent(BookTrip.this, Favorites.class));
                    return  true;
                case R.id.navigation_home:
                    startActivity(new Intent(BookTrip.this, Trips.class));
                    return true;
                case R.id.navigation_change:
                    startActivity(new Intent(BookTrip.this, ChangePassword.class));
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
                    //GetSeatNo(FreeSeats);
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
                boolean exist = false;
                for(int j = 0; j < reservedseates.size(); j++){
                    if(reservedseates.get(j) == no){
                        exist = true;
                    }
                }
                if(exist == false){
                    FreeChairs.add(no);
                    no++;
                    continue;
                }
                else{
                    no++;
                }
            }
            else if (i < reservedseates.size()){
                boolean exist = false;
                for(int j = 0; j < reservedseates.size(); j++){
                    if(reservedseates.get(j) == no){
                        exist = true;
                    }
                }
                if(exist == false){
                    FreeChairs.add(no);
                    no++;
                    continue;
                }
                else{
                    no++;
                }
            }
            else{
                no++;
            }
        }
        return FreeChairs;
    }
}
