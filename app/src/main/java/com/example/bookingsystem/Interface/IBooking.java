package com.example.bookingsystem.Interface;

import com.example.bookingsystem.Booking.Booking;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface IBooking {

    @POST("InsertBook")
    Call<Void> InsertBook(@Body Booking model);

    @GET("ListChairs/{id}")
    Call<List<Integer>> ListChairs(@Path("id") int id);
}
