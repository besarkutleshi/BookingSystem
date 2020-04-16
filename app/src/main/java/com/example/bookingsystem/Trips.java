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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trips);
        textView = findViewById(R.id.txttrips);

        Retrofit retrofit = new Retrofit.Builder().
                baseUrl("https://jsonplaceholder.typicode.com/").
                addConverterFactory(GsonConverterFactory.create()).build();
        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Call<List<Trip>> call = jsonPlaceHolderApi.getPosts();
        call.enqueue(new Callback<List<Trip>>() {
            @Override
            public void onResponse(Call<List<Trip>> call, Response<List<Trip>> response) {
                if(!response.isSuccessful()){
                    textView.setText("Code : " + response.code());
                    return;
                }
                List<Trip> Trips = response.body();
                for(Trip trip : Trips){
                    String content = "";
                    content += "ID : " + trip.getId() + "\n";
                    content += "User ID : " + trip.getUserID() + "\n";
                    content += "Title : " + trip.getTitle() + "\n";
                    content += "Text : " + trip.getText() + "\n\n";

                    textView.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Trip>> call, Throwable t) {
                textView.setText(t.getMessage());
            }
        });
    }
}
