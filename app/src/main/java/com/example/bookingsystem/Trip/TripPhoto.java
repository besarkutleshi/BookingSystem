package com.example.bookingsystem.Trip;

import com.google.gson.annotations.SerializedName;

public class TripPhoto {
    @SerializedName("photo")
    private String Photo;

    public TripPhoto(String photo) {
        Photo = photo;
    }

    public String getPhoto() {
        return Photo;
    }

    public void setPhoto(String photo) {
        Photo = photo;
    }
}
