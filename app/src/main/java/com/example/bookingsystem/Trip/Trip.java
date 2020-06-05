package com.example.bookingsystem.Trip;

import android.content.Intent;

import com.google.gson.annotations.SerializedName;

import java.sql.Date;

public class Trip {

    public static String QueryTable =
            "CREATE TABLE" + " Trips" + "(" + " ID" + " INTEGER," +
                    "NAME" + " Text," +
                    "Date" + " Text," +
                    "Description" + " Text," +
                    "Photo" + " Text," +
                    "Price" + " REAL," +
                    "UserEmail" + " Text" + ")";


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


    public Trip(Integer ID, String name, String date, String description, String photo, double price) {
        this.ID = ID;
        Name = name;
        Date = date;
        Description = description;
        Photo = photo;
        Price = price;
    }

    public Trip(){

    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setDate(String date) {
        Date = date;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setPhoto(String photo) {
        Photo = photo;
    }

    public void setPrice(double price) {
        Price = price;
    }

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
