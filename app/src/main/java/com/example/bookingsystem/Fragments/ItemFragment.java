package com.example.bookingsystem.Fragments;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bookingsystem.R;
import com.example.bookingsystem.Trip.TripRepository;

import java.util.List;

public class ItemFragment extends Fragment {
    public ItemFragment() {

    }

    public static ItemFragment newInstance() {
        ItemFragment fragment = new ItemFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);
        final TripRepository _trips = new TripRepository(container.getContext());
        final RecyclerView recyclerView = view.findViewById(R.id.recyclelist);
        Button btnSearch = view.findViewById(R.id.btnSearch);
        Button btnPastro = view.findViewById(R.id.btnPastro);
        final EditText txtsearch = view.findViewById(R.id.txtsearch);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String search = txtsearch.getText().toString();
                if(search.isEmpty()){
                    Toast.makeText(container.getContext(), "Please fill in search box", Toast.LENGTH_SHORT).show();
                }else{
                    _trips.GetTrips(recyclerView,txtsearch.getText().toString());
                }
            }
        });

        btnPastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String search = txtsearch.getText().toString();
                if(search.isEmpty()){

                }else{
                    txtsearch.setText("");
                    _trips.ShowTrips(recyclerView);
                }
            }
        });
        _trips.ShowTrips(recyclerView);
        return  view;
    }
}
