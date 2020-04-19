package com.example.bookingsystem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ITripAPI {

    @GET("ListTrips")
    Call<List<Trip>> Trips();

}
