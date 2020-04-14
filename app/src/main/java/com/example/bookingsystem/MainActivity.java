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

        btnlogin.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        OpenMainActivity();
                    }
                }
        );

        btnregister.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        OpenRegisterAcitvity();
                    }
                }
        );
    }

    public void OpenRegisterAcitvity(){
        Intent i = new Intent(this, RegisterUser.class);
        startActivity(i);
    }

    public void OpenMainActivity(){
        Intent i = new Intent(this, Booking.class);
        startActivity(i);
    }
}
