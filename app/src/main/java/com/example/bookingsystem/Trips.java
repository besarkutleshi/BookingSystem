package com.example.bookingsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Trips extends AppCompatActivity {
    ITripAPI tripAPI;
    RecyclerView recyclerView;
    final List<Integer> images= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trips);
        images.add(R.drawable.maldivesguide1);
        images.add(R.drawable.brezo);
        images.add(R.drawable.logo);
        images.add(R.drawable.logo);
        images.add(R.drawable.logo);
        recyclerView = findViewById(R.id.recyclerTrips);
        Retrofit.Builder builder = new Retrofit.Builder().
                baseUrl("http://192.168.0.230:45455/api/trips/").
                addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        tripAPI = retrofit.create(ITripAPI.class);
        ShowTrips();

    }

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
                    MyAdapter adapter = new MyAdapter(Trips.this,Trips,images);
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


