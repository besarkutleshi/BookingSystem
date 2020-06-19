package com.example.bookingsystem.Booking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookingsystem.HelperClass;
import com.example.bookingsystem.R;
import com.example.bookingsystem.Trip.TripPhoto;
import com.example.bookingsystem.Trip.TripRepository;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class BookingTrip extends AppCompatActivity {
    private ImageView mainImage;
    private ImageView img2;
    private ImageView img3;
    private ImageView img4;
    private ImageView img5;
    private TextView txtdesc;
    private TextView txttitle;
    private TextView txtprice;
    private String desc;
    private String url;
    private int TripID;
    private BookingRepository _bookRepository;
    private TripRepository _tripRepository;
    private Button btnSave;
    private Button btnBooking;
    private double price;
    private String title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_trip);
        btnSave = findViewById(R.id.btnsave);
        btnBooking = findViewById(R.id.btnBooking);
        mainImage = findViewById(R.id.mainImage);
        txtdesc = findViewById(R.id.txtdescription);
        txtprice = findViewById(R.id.txtprice);
        txttitle = findViewById(R.id.txttitle);
        _bookRepository = new BookingRepository(BookingTrip.this);
        _tripRepository = new TripRepository(BookingTrip.this);
        GetData();
        SetData();



        final Intent i = new Intent(this,BookTrip.class);
        i.putExtra("Price",price);
        i.putExtra("TripID",TripID);
        btnBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(i);
            }
        });
    }


    private void GetData() {
        if(getIntent().hasExtra("Description") && getIntent().hasExtra("Price") && getIntent().hasExtra("Photo")){
            desc = getIntent().getStringExtra("Description");
            price = getIntent().getDoubleExtra("Price",0);
            url = getIntent().getStringExtra("Photo");
            TripID = getIntent().getIntExtra("ID",0);
            String a = getIntent().getStringExtra("Date");
            title = getIntent().getStringExtra("Title");
        }else{
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }
    }

    private void SetData(){
        txtprice.setText("$" + Double.toString(price));
        txtdesc.setText(desc);
        txttitle.setText(title);
        Picasso.get().load(url).resize(1040,498).into(mainImage);
    }
}
