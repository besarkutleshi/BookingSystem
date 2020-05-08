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
import com.example.bookingsystem.Trip.Trips;
import com.squareup.picasso.Picasso;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    private Intent trips;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_trip);
        btnBook = findViewById(R.id.btnbook);
        img = findViewById(R.id.imgTrip);
        txtdesc = findViewById(R.id.txtdescription);txtPrice = findViewById(R.id.txtprice);
        spinner = findViewById(R.id.spinner);
        txtClientName = findViewById(R.id.txtclientname); txtClientSurname = findViewById(R.id.txtclientSurname);
        _bookRepository = new BookingRepository(BookTrip.this);
        GetData();
        SetData();
        _bookRepository.PasData(TripID,spinner);
        trips = new Intent(BookTrip.this,Trips.class);

        btnBook.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Date date = new Date();
                        String datetime = sdf.format(date).toString();
                        String chairno = spinner.getSelectedItem().toString();
                        Booking model = new Booking(0,datetime,desc,TripID,txtClientSurname.getText().toString(),txtClientName.getText().toString(),
                                MainActivity.email,Integer.parseInt(chairno),price);
                        _bookRepository.Book(model,trips);
                    }
                }
        );
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
}
