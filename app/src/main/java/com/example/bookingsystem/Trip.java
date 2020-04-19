package com.example.bookingsystem;

import android.content.Intent;

import com.google.gson.annotations.SerializedName;

import java.sql.Date;

public class Trip {

    @SerializedName("id")
    public Integer ID;
    @SerializedName("name")
    public String Name;
    @SerializedName("date")
    public Date Date;
    @SerializedName("description")
    public String Description;
    @SerializedName("photo")
    public String Photo;

    public Trip(Integer ID, String name, java.sql.Date date, String description, String photo) {
        this.ID = ID;
        Name = name;
        Date = date;
        Description = description;
        Photo = photo;
    }
}
