package com.example.bookingsystem.Booking;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bookingsystem.MainActivity;
import com.example.bookingsystem.R;
public class MyBookingsFragment extends Fragment {

    BookingRepository _bookingRepository;

    public MyBookingsFragment() {
        _bookingRepository = new BookingRepository(getActivity());
    }

    public static MyBookingsFragment newInstance() {
        MyBookingsFragment fragment = new MyBookingsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_bookings, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.mybookoings);
        _bookingRepository.MyBookings(MainActivity.email,recyclerView);
        return view;
    }
}
