package com.example.bookingsystem;

import android.content.Intent;

import com.google.gson.annotations.SerializedName;

import java.sql.Date;

public class Trip {
    @SerializedName("id")
    private Integer ID;
    @SerializedName("name")
    private String Name;
    @SerializedName("date")
    private String Date;
    @SerializedName("description")
    private String Description;
    @SerializedName("photo")
    private String Photo;
    @SerializedName("price")
    private double Price;

    public double getPrice(){return  Price;}
    public Integer getID() {
        return ID;
    }
    public String getName() {
        return Name;
    }
    public String getDate() {
        return Date;
    }
    public String getDescription() {
        return Description;
    }
    public String getPhoto() {
        return Photo;
    }
}
