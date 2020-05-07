package com.example.bookingsystem.Booking;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Booking {
    public int ID;
    public String Date;
    public String Description;
    public int TripID;
    public String ClientName;
    public String ClientSurname;
    public String ClientEmail;
    public int ChairNo;
    public double Price;

    public Booking(int ID, String date, String description, int tripID, String clientName, String clientSurname, String clientEmail, int chairNo, double price) {
        this.ID = ID;
        Date = date;
        Description = description;
        TripID = tripID;
        ClientName = clientName;
        ClientSurname = clientSurname;
        ClientEmail = clientEmail;
        ChairNo = chairNo;
        Price = price;
    }
}
