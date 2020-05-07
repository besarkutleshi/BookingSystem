package com.example.bookingsystem.Trip;

import android.content.Context;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookingsystem.Adapter.MyAdapter;
import com.example.bookingsystem.HelperClass;
import com.example.bookingsystem.Interface.ITripAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TripRepository {

    private Context _context;
    ITripAPI tripAPI;
    public TripRepository(Context _context) {
        this._context = _context;
    }

    public void ShowTrips(final RecyclerView recyclerView){
        tripAPI = HelperClass.GetRetrofit().create(ITripAPI.class);
        Call<List<Trip>> call = tripAPI.Trips();
        call.enqueue(new Callback<List<Trip>>() {
            @Override
            public void onResponse(Call<List<Trip>> call, Response<List<Trip>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(_context, response.message(), Toast.LENGTH_SHORT).show();
                }
                else{
                    List<Trip> Trips = response.body();
                    MyAdapter adapter = new MyAdapter(_context,Trips);
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(_context));
                }
            }

            @Override
            public void onFailure(Call<List<Trip>> call, Throwable t) {
                Toast.makeText(_context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
