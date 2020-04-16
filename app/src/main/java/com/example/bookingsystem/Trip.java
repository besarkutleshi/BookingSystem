package com.example.bookingsystem;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Trip {
    private int userID;
    private int id;
    private  String title;

    @SerializedName("body")
    private String text;

    public int getUserID() {
        return userID;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }
}
