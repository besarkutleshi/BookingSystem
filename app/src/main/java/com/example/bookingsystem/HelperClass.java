package com.example.bookingsystem;
import android.content.Intent;
import android.widget.Toast;
import com.example.bookingsystem.Interface.IAccountAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HelperClass {


    private static Intent login;
    public static Retrofit GetRetrofit(){
        Retrofit.Builder builder = new Retrofit.Builder().
                baseUrl("http://192.168.0.228:45455/trips/").
                addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        return  retrofit;
    }

    public static void Logout(){
        IAccountAPI accountAPI = GetRetrofit().create(IAccountAPI.class);
        Call<Void> call = accountAPI.Logout();
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(null, "Not Logout", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(null, "Logout", Toast.LENGTH_SHORT).show();
                   // MainActivity.email = null;
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(null, "Not Logout", Toast.LENGTH_SHORT).show();
            }
        });
    }



}
