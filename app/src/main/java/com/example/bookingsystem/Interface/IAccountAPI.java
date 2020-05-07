package com.example.bookingsystem.Interface;

import com.example.bookingsystem.Account.ChangePasswordModel;
import com.example.bookingsystem.Account.LoginModel;
import com.example.bookingsystem.Account.RegisterModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface IAccountAPI {

    @POST("Login")
    Call<Void> Login(@Body LoginModel model);

    @POST("RegisterUser")
    Call<RegisterModel> RegisterModel(@Body RegisterModel model);

    @POST("Logout")
    Call<Void> Logout();

    @POST("ChangePassword")
    Call<ChangePasswordModel> ChangePassword(@Body ChangePasswordModel model);

}
