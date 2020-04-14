package com.example.bookingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnregister;
    Button btnlogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnregister = findViewById(R.id.btnLoginRegister);
        btnlogin = findViewById(R.id.btnLogin);

        final Intent i = new Intent(this, Booking.class);
        final Intent in = new Intent(this, RegisterUser.class);
        btnlogin.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        OpenActivity(i);
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
