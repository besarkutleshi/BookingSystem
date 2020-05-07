package com.example.bookingsystem.Account;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.bookingsystem.HelperClass;
import com.example.bookingsystem.Interface.IAccountAPI;
import com.example.bookingsystem.Interface.IBooking;
import com.example.bookingsystem.MainActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AccountRepository {

    private Context _context;
    private Retrofit retrofit = HelperClass.GetRetrofit();
    private IAccountAPI accountAPI;

    public AccountRepository(Context context){
        _context = context;
    }

    public void Login(LoginModel model, final Intent i){
        accountAPI = retrofit.create(IAccountAPI.class);
        Call<Void> call = accountAPI.Login(model);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(_context, response.message(), Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(_context, "Login Successful", Toast.LENGTH_SHORT).show();
                    _context.startActivity(i);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(_context, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }


    public void ChangePassword(ChangePasswordModel model, final Intent intent){
        accountAPI = retrofit.create(IAccountAPI.class);
        Call<Void> call = accountAPI.ChangePassword(model);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(_context, "Not Change \n" + response.message(), Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(_context, "Change Successful", Toast.LENGTH_SHORT).show();
                    _context.startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(_context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void Register(RegisterModel model, final Intent intent){
        accountAPI = retrofit.create(IAccountAPI.class);
        Call<Void> call = accountAPI.RegisterModel(model);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(_context, "Register Failed", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(_context, "Register Successful", Toast.LENGTH_SHORT).show();
                    _context.startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(_context, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

}
