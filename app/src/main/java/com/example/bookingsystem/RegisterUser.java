package com.example.bookingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterUser extends AppCompatActivity {
    Button btnRegister;
    Button btnClose;
    EditText txtusername;
    EditText txtpassword;
    EditText txtconfirmpassword;
    IAccountAPI accountAPI;
    RegisterModel model;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        btnRegister = findViewById(R.id.btnRegisterUser);
        btnClose = findViewById(R.id.btnClose);
        txtusername = findViewById(R.id.txtusernameR);
        txtpassword = findViewById(R.id.txtpasswordR);
        txtconfirmpassword = findViewById(R.id.txtconfirmpasswordR);
        final Intent i = new Intent(this,MainActivity.class);
        accountAPI = HelperClass.GetRetrofit().create(IAccountAPI.class);

        btnRegister.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        model = new RegisterModel(txtusername.getText().toString(),txtpassword.getText().toString(),txtconfirmpassword.getText().toString());
                        Register(model);
                        txtpassword.setText(""); txtconfirmpassword.setText("");txtusername.setText("");
                    }
                }
        );

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

    public void Register(RegisterModel model){
        Call<RegisterModel> call = accountAPI.RegisterModel(model);
        call.enqueue(new Callback<RegisterModel>() {
            @Override
            public void onResponse(Call<RegisterModel> call, Response<RegisterModel> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(RegisterUser.this, "Not Successful", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(RegisterUser.this, "Register Successful", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RegisterModel> call, Throwable t) {
                Toast.makeText(RegisterUser.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

}
