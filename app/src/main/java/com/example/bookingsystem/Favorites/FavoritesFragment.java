package com.example.bookingsystem.Favorites;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookingsystem.Adapter.FavoritesAdapter;
import com.example.bookingsystem.Adapter.MyAdapter;
import com.example.bookingsystem.R;
import com.example.bookingsystem.SQLite.DataBaseHelper;
import com.example.bookingsystem.Trip.Trip;

import java.util.List;

public class FavoritesFragment extends Fragment {
    DataBaseHelper db;
    public FavoritesFragment(){
    }

    public static FavoritesFragment NewInstance(){
        FavoritesFragment obj = new FavoritesFragment();
        return  obj;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragement_favorites_ljst, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclelistfavorites);
        db = new DataBaseHelper(container.getContext());
        List<Trip> trips = db.ShowFavoritesTrip();
        FavoritesAdapter adapter = new FavoritesAdapter(trips,container.getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));
        return  view;
    }
}
