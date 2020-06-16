package com.example.bookingsystem.Booking;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.example.bookingsystem.MainActivity;
import com.example.bookingsystem.R;
import com.example.bookingsystem.Trip.TripRepository;
import com.example.bookingsystem.Trip.Trips;
import com.squareup.picasso.Picasso;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class BookTrip extends AppCompatActivity {
    private static final DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    private TripRepository _trip = new TripRepository(this);
    private BookingRepository _book = new BookingRepository(this);
    private Spinner spinner;
    private EditText txtname;
    private EditText txtsurname;
    private EditText txtemail;
    private EditText txtdescription;
    private Button btnBook;
    private Button btnClear;
    private int tripid;
    private double tripprice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_trip);
        spinner = findViewById(R.id.chairs);
        txtname = findViewById(R.id.txtname);
        txtsurname = findViewById(R.id.txtsurname);
        txtemail = findViewById(R.id.txtemailbook);
        txtdescription = findViewById(R.id.txtdescriptionbook);
        btnBook = findViewById(R.id.btnBook);
        btnClear = findViewById(R.id.btnClear);
        GetData();
        _book.PasData(tripid,spinner);
        final Intent trips = new Intent(this,Trips.class);
        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Booking obj = new Booking(0,"",txtdescription.getText().toString(),tripid,txtname.getText().toString(),
                        txtsurname.getText().toString(),txtemail.getText().toString(),Integer.parseInt(spinner.getSelectedItem().toString()),tripprice);
                if(obj.Description.isEmpty() || obj.ChairNo == 0 || obj.ClientEmail.isEmpty() || obj.ClientSurname.isEmpty() ||
                        obj.ClientName.isEmpty() || obj.TripID == 0 || obj.Price == 0){
                    Toast.makeText(BookTrip.this, "Please fill in empty box's", Toast.LENGTH_SHORT).show();
                }
                else{
                    _book.Book(obj,trips);
                }
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtdescription.setText("");
                txtemail.setText("");
                txtname.setText("");
                txtsurname.setText("");
            }
        });

    }


    private void GetData(){
        if(getIntent().hasExtra("TripID") && getIntent().hasExtra("Price")){
            tripprice = getIntent().getDoubleExtra("Price",0);
            tripid = getIntent().getIntExtra("TripID",0);
        }
    }
}
