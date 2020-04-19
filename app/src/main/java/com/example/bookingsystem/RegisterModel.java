package com.example.bookingsystem;

import com.google.gson.annotations.SerializedName;

public class RegisterModel {

    public String Email;
    public String Password;
    public String ConfirmPassword;

    public RegisterModel(String email, String password, String confirmPassword){
        this.Email = email;
        this.Password = password;
        this.ConfirmPassword=confirmPassword;
    }
}

