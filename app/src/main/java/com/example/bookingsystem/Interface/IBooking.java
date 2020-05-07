package com.example.bookingsystem.Interface;

import com.example.bookingsystem.Booking.Booking;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface IBooking {

    @POST("InsertBook")
    Call<Booking> InsertBook(@Body Booking model);
}
