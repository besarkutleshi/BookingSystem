package com.example.bookingsystem;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface IAccountAPI {

    @POST("Login")
    Call<Void> Login(@Body LoginModel model);

}
