package com.example.bookingsystem;

public class Booking_Details {
    public int BookingID;
    public int TripID;
    public String ClientName;
    public String ClientSurname;
    public int ChairNo;
    public double Price;

    public Booking_Details(int bookingID, int tripID, String clientName, String clientSurname, int chairNo, double price) {
        BookingID = bookingID;
        TripID = tripID;
        ClientName = clientName;
        ClientSurname = clientSurname;
        ChairNo = chairNo;
        Price = price;
    }
}
