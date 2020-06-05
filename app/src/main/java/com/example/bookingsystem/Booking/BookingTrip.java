package com.example.bookingsystem.Booking;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookingsystem.R;
import com.example.bookingsystem.Trip.TripRepository;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class BookingTrip extends AppCompatActivity {

    private ImageView firstImage;
    private ImageView secondImage;
    private ImageView thirdImage;
    private ImageView fourthImage;
    private ImageView mainImage;
    private TextView txtdesc;
    private String desc;
    private String url;
    private int TripID;
    private BookingRepository _bookRepository;
    private TripRepository _tripRepository;
    public static List<String> photos;
    public BookingTrip(List<String> list) {
        photos = list;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_trip);
        firstImage = findViewById(R.id.firstimage);
        secondImage = findViewById(R.id.secondimage);
        thirdImage = findViewById(R.id.thirdimage);
        fourthImage = findViewById(R.id.fourthimage);
        mainImage = findViewById(R.id.mainImage);
        txtdesc = findViewById(R.id.description);
        _bookRepository = new BookingRepository(BookingTrip.this);
        _tripRepository = new TripRepository(BookingTrip.this);
        GetData();
        SetData();
        //final String url1 = "http://192.168.0.228:45455/trips/GetPhoto/brezo1.jpg";
        //Picasso.get().load(url1).into(firstImage);
        //List<String> array = new ArrayList<>();
        //array = _tripRepository.GetPhotos(TripID);
        //SetPhotos(photos);
    }


    private void GetData() {
        if(getIntent().hasExtra("Description") && getIntent().hasExtra("Price") && getIntent().hasExtra("Photo")){
            desc = getIntent().getStringExtra("Description");
            //price = getIntent().getDoubleExtra("Price",0);
            url = getIntent().getStringExtra("Photo");
            TripID = getIntent().getIntExtra("ID",0);
            String a = getIntent().getStringExtra("Date");
        }else{
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }
    }

    private void SetData(){
        //txtPrice.setText("Price : " + Double.toString(price));
        txtdesc.setText(desc);
        Picasso.get().load(url).resize(1040,498).into(mainImage);
    }

    public  void SetPhotos(List<String> photos){
        photos = new ArrayList<>();
        ImageView[] images = new ImageView[4];
        images[0] = firstImage;
        images[1] = secondImage;
        images[2] = thirdImage;
        images[3] = fourthImage;
        for (int i=1; i< photos.size();i++){
            final String url1 = "http://192.168.0.228:45455/trips/GetPhoto/" + photos.get(i);
            Picasso.get().load(url1).into(images[i-1]);
        }
        //final String url1 = "http://192.168.0.228:45455/trips/GetPhoto/brezo1.jpg";
        //Picasso.get().load(url1).into(firstImage);
    }
}
