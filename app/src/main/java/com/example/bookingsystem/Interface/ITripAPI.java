package com.example.bookingsystem.Interface;

import com.example.bookingsystem.Booking.Booking;
import com.example.bookingsystem.Trip.Trip;
import com.example.bookingsystem.Trip.TripPhoto;

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

    @GET("GetPhotos/{id}")
    Call<List<TripPhoto>> GetPhotos(@Path("id") int id);

    @GET("GetPhoto/{photo}")
    Call<List<String>> GetPhoto(@Path("photo") String photo);

    @GET("GetTrips/{name}")
    Call<List<Trip>> GetTrips(@Path("name") String name);
}
