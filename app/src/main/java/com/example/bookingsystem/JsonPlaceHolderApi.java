package com.example.bookingsystem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface JsonPlaceHolderApi {
    @GET("users")
    Call<List<RegisterModel>> getPosts();

    @POST("RegisterUser")
    Call<RegisterModel> RegisterModel(@Body RegisterModel model);
}
