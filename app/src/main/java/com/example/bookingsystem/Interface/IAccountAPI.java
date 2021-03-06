package com.example.bookingsystem.Interface;

import com.example.bookingsystem.Account.Model.ChangePasswordModel;
import com.example.bookingsystem.Account.Model.LoginModel;
import com.example.bookingsystem.Account.Model.RegisterModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface IAccountAPI {

    @POST("Login")
    Call<Void> Login(@Body LoginModel model);

    @POST("RegisterUser")
    Call<Void> RegisterModel(@Body RegisterModel model);

    @POST("Logout")
    Call<Void> Logout();

    @POST("ChangePassword")
    Call<Void> ChangePassword(@Body ChangePasswordModel model);

}
