package com.example.bookingsystem;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.bookingsystem.Account.ChangePasswordFragment;


public class SettingsFragment extends Fragment {

    public SettingsFragment() {
    }


    public static SettingsFragment newInstance() {
        SettingsFragment fragment = new SettingsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_settings, container, false);
        TextView txtemail = view.findViewById(R.id.txtemal);
        txtemail.setText(MainActivity.email);
        Button btnChangePassword = view.findViewById(R.id.btnChangeMyPassword);
        btnChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangePasswordFragment obj = new ChangePasswordFragment();
                openFragment(obj);
            }
        });
        Button btnMyBookings = view.findViewById(R.id.btnMyBookings);
        Button btnLogout = view.findViewById(R.id.btnLogoutS);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HelperClass.Logout();
            }
        });
        return  view;

    }

    public void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }




}
