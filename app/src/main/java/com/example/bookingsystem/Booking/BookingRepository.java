package com.example.bookingsystem.Booking;

import android.content.Context;
import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookingsystem.Adapter.MyAdapter;
import com.example.bookingsystem.Adapter.MyBookingsAdapter;
import com.example.bookingsystem.HelperClass;
import com.example.bookingsystem.Interface.IBooking;
import com.example.bookingsystem.Interface.ITripAPI;
import com.example.bookingsystem.R;
import com.example.bookingsystem.Trip.Trip;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class BookingRepository {
    Context _context;
    IBooking bookingAPI;
    public BookingRepository(Context context){
        _context = context;
    }


    public void Book(Booking model, final Intent trips){
        bookingAPI = HelperClass.GetRetrofit().create(IBooking.class);
        Call<Void> call = bookingAPI.InsertBook(model);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(_context, "Booking Failed", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(_context, "Booking Successful", Toast.LENGTH_SHORT).show();
                    _context.startActivity(trips);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }


    public ArrayList<Integer> PasData(final int id,final Spinner spinner){
        Retrofit retrofit = HelperClass.GetRetrofit();
        IBooking iBooking = retrofit.create(IBooking.class);
        Call<List<Integer>> call = iBooking.ListChairs(id);
        final ArrayList<Integer> Chairs = new ArrayList<>();
        call.enqueue(new Callback<List<Integer>>() {
            @Override
            public void onResponse(Call<List<Integer>> call, Response<List<Integer>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(_context, "Something went wrong", Toast.LENGTH_SHORT).show();
                }else{
                    List<Integer> chairs = new ArrayList<>();
                    chairs = response.body();
                    ArrayList<Integer> FreeSeats = FreeChairs(chairs);
                    ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<Integer>(
                            _context,
                            R.layout.spinner_item,
                            FreeSeats
                    );
                    spinner.setAdapter(arrayAdapter);
                }
            }
            @Override
            public void onFailure(Call<List<Integer>> call, Throwable t) {
                Toast.makeText(_context, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
        return  Chairs;
    }


    private ArrayList<Integer> FreeChairs(List<Integer> reservedseates){
        ArrayList<Integer> FreeChairs = new ArrayList<>();
        int no = 1;
        for (int i = 0; i <= 10; i++){
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
        return FreeChairs;
    }

    public void MyBookings(String email, final RecyclerView recyclerView){
        bookingAPI = HelperClass.GetRetrofit().create(IBooking.class);
        Call<List<Trip>> call = bookingAPI.MyTrips(email);
        call.enqueue(new Callback<List<Trip>>() {
            @Override
            public void onResponse(Call<List<Trip>> call, Response<List<Trip>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(_context, response.message(), Toast.LENGTH_SHORT).show();
                }
                else{
                    List<Trip> Trips = response.body();
                    MyBookingsAdapter adapter = new MyBookingsAdapter(Trips,_context);
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(_context));
                }
            }

            @Override
            public void onFailure(Call<List<Trip>> call, Throwable t) {

            }
        });
    }

}
