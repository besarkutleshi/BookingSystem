package com.example.bookingsystem.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.bookingsystem.R;
import com.example.bookingsystem.SQLite.DataBaseHelper;
import com.example.bookingsystem.Trip.Trip;
import com.squareup.picasso.Picasso;
import java.util.List;

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.MyViewHolder> {

    public List<Trip> Trips;
    private android.content.Context Context;
    private DataBaseHelper db;

    public FavoritesAdapter(List<Trip> trips, android.content.Context context) {
        Trips = trips;
        Context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.my_favoirtestrip,parent,false);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.txtName.setText(Trips.get(position).getName());
        String[] date = Trips.get(position).getDate().split("T");
        holder.txtDate.setText(date[0]);
        String url = "http://192.168.0.229:45455/trips/GetPhoto/" + Trips.get(position).getPhoto();
        Picasso.get().load(url).resize(1210,498).into(holder.image);
        holder.Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = new DataBaseHelper(Context);
                if(db.DeleteTrip(Trips.get(position).getID())){
                    Toast.makeText(Context, "Trip deleted succesful", Toast.LENGTH_SHORT).show();

                }
                else{
                    Toast.makeText(Context, "Trip not deleted succesful", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return Trips.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private Button Delete;
        private TextView txtName;
        private TextView txtDate;
        private ImageView image;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Delete = itemView.findViewById(R.id.buttonDelete);
            txtName = itemView.findViewById(R.id.txtNameTfav);
            txtDate = itemView.findViewById(R.id.txtDatefavT);
            image = itemView.findViewById(R.id.Tripimgfav);
        }
    }
}
