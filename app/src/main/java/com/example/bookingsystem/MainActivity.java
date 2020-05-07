package com.example.bookingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bookingsystem.Account.LoginModel;
import com.example.bookingsystem.Account.RegisterUser;
import com.example.bookingsystem.Booking.BookTrip;
import com.example.bookingsystem.Interface.IAccountAPI;
import com.example.bookingsystem.Trip.Trips;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    public static String email;
    Button btnregister;
    Button btnlogin;
    EditText txtUserName;
    EditText txtPasswordL;
    IAccountAPI accountAPI;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnregister = findViewById(R.id.btnLoginRegister);
        btnlogin = findViewById(R.id.btnLogin);
        txtUserName = findViewById(R.id.txtLoginUsername);
        txtPasswordL = findViewById(R.id.txtLoginPassword);
        final Intent i = new Intent(this, Trips.class);
        final Intent in = new Intent(this, RegisterUser.class);
        Retrofit.Builder builder = new Retrofit.Builder().
                baseUrl("http://192.168.0.229:45455/api/trips/").
                addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        accountAPI = retrofit.create(IAccountAPI.class);

        final Intent ib = new Intent(this, BookTrip.class);
        btnlogin.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final LoginModel model = new LoginModel(txtUserName.getText().toString(),txtPasswordL.getText().toString());
                        email = txtUserName.getText().toString();
                        Login(model,i);
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

    public void Login(LoginModel model, final Intent i){
        Call<Void> call = accountAPI.Login(model);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(MainActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    txtPasswordL.setText(""); txtUserName.setText("");
                    OpenActivity(i);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
