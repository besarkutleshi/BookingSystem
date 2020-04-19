package com.example.bookingsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Trips extends AppCompatActivity {
    ITripAPI tripAPI;
    List<Trip> Trips = new LinkedList<Trip>();
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trips);
        recyclerView = findViewById(R.id.recyclerTrips);
        Retrofit.Builder builder = new Retrofit.Builder().
                baseUrl("http://192.168.0.230:45455/api/trips/").
                addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        tripAPI = retrofit.create(ITripAPI.class);
        ListTrips();
        MyAdapter adapter = new MyAdapter(this,Trips);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    public void ListTrips(){
        Call<List<Trip>> call = tripAPI.Trips();
        call.enqueue(new Callback<List<Trip>>() {
            @Override
            public void onResponse(Call<List<Trip>> call, Response<List<Trip>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(Trips.this, response.message(), Toast.LENGTH_SHORT).show();
                }
                else{
                    Trips = response.body();
                }
            }

            @Override
            public void onFailure(Call<List<Trip>> call, Throwable t) {
                Toast.makeText(Trips.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                return;
            }
        });
    }
}
