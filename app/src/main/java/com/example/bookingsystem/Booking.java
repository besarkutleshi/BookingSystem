package com.example.bookingsystem;

import java.util.ArrayList;
import java.util.List;

public class Booking {
    public String Date;
    public String Description;
    public List<Booking_Details> BookingDetails;

    public Booking(String date, String description) {
        Date = date;
        Description = description;
        BookingDetails = new ArrayList<>();
    }
}
