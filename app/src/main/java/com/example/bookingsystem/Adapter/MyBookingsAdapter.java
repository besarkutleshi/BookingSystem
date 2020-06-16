package com.example.bookingsystem.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookingsystem.Booking.BookingRepository;
import com.example.bookingsystem.R;
import com.example.bookingsystem.Trip.Trip;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyBookingsAdapter extends RecyclerView.Adapter<MyBookingsAdapter.MyViewHolder> {

    private List<Trip> MyTrips;
    private android.content.Context Context;
    private BookingRepository _bookingRepository;
    public MyBookingsAdapter(List<Trip> trips, android.content.Context context){
        MyTrips = trips;
        Context = context;
        _bookingRepository = new BookingRepository(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.mybooking_list,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String[] date = MyTrips.get(position).getDate().split("T");
        holder.txtdate.setText(date[0]);
        holder.txtname.setText(MyTrips.get(position).getName());
        String url = "http://192.168.0.228:45455/trips/GetPhoto/" + MyTrips.get(position).getPhoto();
        Picasso.get().load(url).resize(1040,498).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return MyTrips.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView txtname;
        TextView txtdate;
        ImageView img;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtdate = itemView.findViewById(R.id.mytxtdate);
            txtname = itemView.findViewById(R.id.mytxtname);
            img = itemView.findViewById(R.id.myBookingIMG);
        }
    }
}
