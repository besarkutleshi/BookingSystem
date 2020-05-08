package com.example.bookingsystem.Fragments;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclelist);
        TripRepository _trips = new TripRepository(container.getContext());
        _trips.ShowTrips(recyclerView);
        return  view;
    }
}
