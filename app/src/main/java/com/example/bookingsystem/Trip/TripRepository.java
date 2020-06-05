package com.example.bookingsystem.Trip;

import android.content.Context;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookingsystem.Adapter.MyAdapter;
import com.example.bookingsystem.Booking.BookTrip;
import com.example.bookingsystem.Booking.BookingTrip;
import com.example.bookingsystem.HelperClass;
import com.example.bookingsystem.Interface.ITripAPI;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TripRepository {

    private Context _context;
    public static List<String> photos = new ArrayList<>();
    ITripAPI tripAPI;
    public TripRepository(Context context) {
        this._context = context;
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

    public List<String> GetPhotos(int id){
        final String[] array = null;
        tripAPI = HelperClass.GetRetrofit().create(ITripAPI.class);
        Call<List<String>> call = tripAPI.GetPhotos(id);
        call.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(_context, response.message(), Toast.LENGTH_SHORT).show();
                }
                else{
                    //List<String> list = response.body();
                    //int index = 0;
                    //for(String s : list){
                    //    array[index++] = s;
                    //}
                    photos.clear();
                    photos.addAll(response.body());
                    //bookTrip.SetPhotos(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                Toast.makeText(_context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        return photos;
    }


    public String[] PhotosArray(List<String> list){
        String[] images = new String[list.size()];
        for (int i = 1; i < list.size(); i++){
            images[i] = photos.get(i);
        }
        return  images;
    }
}
