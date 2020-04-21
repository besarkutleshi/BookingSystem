package com.example.bookingsystem;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ITripAPI {

    @GET("ListTrips")
    Call<List<Trip>> Trips();

    @GET("ListPhotosTrips")
    Call<String> PhotosTrips();

    @GET("ListChairs/{id}")
    Call<List<Integer>> ListChairs(@Path("id") int id);

}
