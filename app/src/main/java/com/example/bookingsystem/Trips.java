package com.example.bookingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.POST;

public class Trips extends AppCompatActivity {
    TextView textView;
    JsonPlaceHolderApi jsonPlaceHolderApi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trips);
        textView = findViewById(R.id.txttrips);

        Retrofit retrofit = new Retrofit.Builder().
                baseUrl("http://192.168.0.230:45455/api/trips/").
                addConverterFactory(GsonConverterFactory.create()).build();
        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Call<List<RegisterModel>> call = jsonPlaceHolderApi.getPosts();
        call.enqueue(new Callback<List<RegisterModel>>() {
            @Override
            public void onResponse(Call<List<RegisterModel>> call, Response<List<RegisterModel>> response) {
                if(!response.isSuccessful()){

                }
                else{
                    List<RegisterModel> models = response.body();
                    for(RegisterModel model : models){
                        String content = "";
                        content += "\n";
                        textView.append(content);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<RegisterModel>> call, Throwable t) {

            }
        });
    }
}
