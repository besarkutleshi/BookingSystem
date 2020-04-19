package com.example.bookingsystem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    public List<Trip> Trips;
    Context Context;
    public MyAdapter(Context context,List<Trip> trips){
        Trips = trips;
        this.Context = context;
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
        holder.Name.setText(Trips.get(position).Name);
        holder.Date.setText(Trips.get(position).Date.toString());
        holder.Image.setImageResource(Integer.parseInt(Trips.get(position).Photo.toString()));
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
