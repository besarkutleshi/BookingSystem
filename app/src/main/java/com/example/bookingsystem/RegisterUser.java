package com.example.bookingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RegisterUser extends AppCompatActivity {
    Button btnRegister;
    Button btnClose;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        btnRegister = findViewById(R.id.btnRegisterUser);
        btnClose = findViewById(R.id.btnClose);
        final Intent i = new Intent(this,MainActivity.class);
        btnClose.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        OpenActivity(i);
                    }
                }
        );
    }

    public void OpenActivity(Intent i){
        startActivity(i);
    }
}
