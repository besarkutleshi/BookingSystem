package com.example.bookingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bookingsystem.Account.Model.AccountRepository;
import com.example.bookingsystem.Account.Model.LoginModel;
import com.example.bookingsystem.Account.RegisterUser;
import com.example.bookingsystem.Booking.BookTrip;
import com.example.bookingsystem.Trip.Trips;

public class MainActivity extends AppCompatActivity {
    public static String email = "diqka";
    private Button btnregister;
    private Button btnlogin;
    private EditText txtUserName;
    private EditText txtPasswordL;
    private AccountRepository _accountRep;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnregister = findViewById(R.id.btnLoginRegister);
        btnlogin = findViewById(R.id.btnLogin);
        txtUserName = findViewById(R.id.txtLoginUsername);
        txtPasswordL = findViewById(R.id.txtLoginPassword);
        _accountRep = new AccountRepository(MainActivity.this);
        final Intent i = new Intent(this, Trips.class);
        final Intent in = new Intent(this, RegisterUser.class);

        final Intent ib = new Intent(this, BookTrip.class);

        btnlogin.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final LoginModel model = new LoginModel(txtUserName.getText().toString(), txtPasswordL.getText().toString());
                        email = txtUserName.getText().toString();
                        String password = txtPasswordL.getText().toString();
                        if(email.isEmpty() || password.isEmpty()){
                            Toast.makeText(MainActivity.this, "Please fill in empty box's", Toast.LENGTH_SHORT).show();
                        }else {
                            _accountRep.Login(model, i);
                            txtPasswordL.setText("");
                            txtUserName.setText("");
                        }
                    }
                }
        );

        btnregister.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        OpenActivity(in);
                    }
                }
        );
    }

    public void OpenActivity(Intent i){
        startActivity(i);
    }
}
