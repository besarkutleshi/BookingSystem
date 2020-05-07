package com.example.bookingsystem.Booking;

public class Booking_Details {
    public int BookingID;
    public int TripID;
    public String ClientName;
    public String ClientSurname;
    public String ClientEmail;
    public int ChairNo;
    public double Price;

    public Booking_Details(int bookingID, int tripID, String clientName, String clientSurname,String clientEmail ,int chairNo, double price) {
        BookingID = bookingID;
        TripID = tripID;
        ClientName = clientName;
        ClientSurname = clientSurname;
        ClientEmail = clientEmail;
        ChairNo = chairNo;
        Price = price;
    }
}
