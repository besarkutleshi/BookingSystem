package com.example.bookingsystem.Interface;

import com.example.bookingsystem.Booking.Booking;
import com.example.bookingsystem.Trip.Trip;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ITripAPI {

    @GET("ListTrips")
    Call<List<Trip>> Trips();

    @GET("ListPhotosTrips")
    Call<String> PhotosTrips();

    @GET("ListChairs/{id}")
    Call<List<Integer>> ListChairs(@Path("id") int id);
}
