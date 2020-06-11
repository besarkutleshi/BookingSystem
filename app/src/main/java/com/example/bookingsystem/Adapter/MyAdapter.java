package com.example.bookingsystem.Adapter;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookingsystem.Booking.BookTrip;
import com.example.bookingsystem.Booking.BookingTrip;
import com.example.bookingsystem.R;
import com.example.bookingsystem.SQLite.DataBaseHelper;
import com.example.bookingsystem.Trip.Trip;
import com.example.bookingsystem.Trip.TripRepository;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    public List<Trip> Trips;
    private Context Context;
    private TripRepository _tripRep = new TripRepository(getContext());
    private DataBaseHelper db;
    public android.content.Context getContext() {
        return Context;
    }

    public MyAdapter(Context context, List<Trip> trips){
        Trips = trips;
        this.Context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.mytrips,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        int index = position + 1;
        final String url = "http://192.168.0.228:45455/trips/ListPhotosTrips/" + index;
        Picasso.get().load(url).resize(1040,498).into(holder.Image);
        holder.Name.setText(Trips.get(position).getName());
        String date = (Trips.get(position).getDate().toString());
        final String[] a = date.split("T");
        holder.Date.setText(a[0].toString());
        holder.btnLearn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(getContext(),BookingTrip.class);
                        i.putExtra("ID",Trips.get(position).getID());
                        i.putExtra("Description",Trips.get(position).getDescription().toString());
                        i.putExtra("Price",Trips.get(position).getPrice());
                        i.putExtra("Photo",url.toString());
                        i.putExtra("Date",Trips.get(position).getDate().toString());
                        i.putExtra("Title",Trips.get(position).getName().toString());
                        getContext().startActivity(i);
                    }
                }
        );
        holder.btnFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Trip obj = new Trip(Trips.get(position).getID(),Trips.get(position).getName(),Trips.get(position).getDate(),
                        Trips.get(position).getDescription(),Trips.get(position).getPhoto(),Trips.get(position).getPrice());
                db = new DataBaseHelper(getContext());
                if(db.InsertTrip(obj)){
                    Toast.makeText(getContext(), "You saved a trip", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getContext(), "You can not save twice", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    @Override
    public int getItemCount() {
        return Trips.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        List<ImageView> photos = new ArrayList<>();
        TextView Name;
        TextView Date;
        ImageView Image;
        Button btnLearn;
        Button btnFav;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Name = itemView.findViewById(R.id.txtNameT);
            Date = itemView.findViewById(R.id.txtDateT);
            Image = itemView.findViewById(R.id.Tripimg);
            btnLearn = itemView.findViewById(R.id.buttonLearn);
            btnFav = itemView.findViewById(R.id.btnFav);
        }
    }
}
