package com.example.bookingsystem.Booking;
import androidx.appcompat.app.AppCompatActivity;
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
    private Spinner spinner;
    private ImageView img;
    private TextView txtdesc;
    private TextView txtPrice;
    private String desc;
    private double price;
    private String url;
    private Button btnBook;
    private EditText txtClientName;
    private EditText txtClientSurname;
    private int TripID;
    private BookingRepository _bookRepository;
    private TripRepository _tripRepository;
    private Intent trips;
    private ImageView image1;
    private ImageView image2;
    private ImageView image3;
    private ImageView image4;
    private static List<String> array = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_trip);
        //btnBook = findViewById(R.id.btnbook);
        //img = findViewById(R.id.imgTrip);
        //image1 = findViewById(R.id.image1);
        //image2 = findViewById(R.id.image2);
        //image3 = findViewById(R.id.image3);
        //image4 = findViewById(R.id.image4);
       //txtdesc = findViewById(R.id.txtdescription);txtPrice = findViewById(R.id.txtprice);
        //spinner = findViewById(R.id.spinner);
       // txtClientName = findViewById(R.id.txtclientname); txtClientSurname = findViewById(R.id.txtclientSurname);
        //_bookRepository = new BookingRepository(BookTrip.this);
        //_tripRepository = new TripRepository(BookTrip.this);
       // GetData();
        //SetData();
        //_bookRepository.PasData(TripID,spinner);
        //List<String> photos = _tripRepository.GetPhotos(TripID);
        //trips = new Intent(BookTrip.this,Trips.class);
        //array = _tripRepository.GetPhotos(TripID);
        //SetPhotos(array);
        //SetPhotos(array);
        //btnBook.setOnClickListener(
        //        new View.OnClickListener() {
        //            @Override
        //            public void onClick(View v) {
        //                Date date = new Date();
        //                String datetime = sdf.format(date).toString();
        //                String chairno = spinner.getSelectedItem().toString();
        //                Booking model = new Booking(0,datetime,desc,TripID,txtClientSurname.getText().toString(),txtClientName.getText().toString(),
        //                        MainActivity.email,Integer.parseInt(chairno),price);
        //                _bookRepository.Book(model,trips);
        //            }
        //        }
        //);
    }

    private void GetData() {
        if(getIntent().hasExtra("Description") && getIntent().hasExtra("Price") && getIntent().hasExtra("Photo")){
            desc = getIntent().getStringExtra("Description");
            price = getIntent().getDoubleExtra("Price",0);
            url = getIntent().getStringExtra("Photo");
            TripID = getIntent().getIntExtra("ID",0);
            String a = getIntent().getStringExtra("Date");
        }else{
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }
    }

    private void SetData(){
        txtPrice.setText("Price : " + Double.toString(price));
        txtdesc.setText(desc);
        Picasso.get().load(url).resize(1040,498).into(img);
    }

    public void SetPhotos(List<String> photos){
        ImageView[] images = new ImageView[4];
        images[0] = image1;
        images[1] = image2;
        images[2] = image3;
        images[3] = image4;
        for (int i=1; i< photos.size();i++){
            final String url1 = "http://192.168.229.161:45455/api/trips/GetPhoto/" + photos.get(i);
            Picasso.get().load(url1).into(images[i]);
        }
    }
}
