package com.example.bookingsystem;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.io.ByteArrayInputStream;
import java.nio.charset.Charset;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    public List<Trip> Trips;
    public List<Integer> Image;
    private Context Context;

    public android.content.Context getContext() {
        return Context;
    }

    public MyAdapter(Context context, List<Trip> trips, List<Integer> images){
        Trips = trips;
        this.Context = context;
        this.Image = images;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(Context);
        View view = inflater.inflate(R.layout.mytrips,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        int index = position + 1;
        String url = "http://192.168.0.230:45455/api/trips/ListPhotosTrips/" + index;
        Picasso.get().load(url).resize(1040,498).into(holder.Image);
        holder.Name.setText(Trips.get(position).getName());
        String date = (Trips.get(position).getDate().toString());
        String[] a = date.split("T");
        holder.Date.setText(a[0].toString());
       // holder.Image.setImageResource(Image.get(position));
    }

    @Override
    public int getItemCount() {
        return Trips.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView Name;
        TextView Date;
        ImageView Image;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Name = itemView.findViewById(R.id.txtNameT);
            Date = itemView.findViewById(R.id.txtDateT);
            Image = itemView.findViewById(R.id.Tripimg);
        }
    }


}
