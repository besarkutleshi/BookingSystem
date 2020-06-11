package com.example.bookingsystem.Account;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.constraintlayout.solver.widgets.Helper;
import androidx.fragment.app.Fragment;

import com.example.bookingsystem.Account.Model.AccountRepository;
import com.example.bookingsystem.Fragments.ItemFragment;
import com.example.bookingsystem.HelperClass;
import com.example.bookingsystem.MainActivity;
import com.example.bookingsystem.R;

public class LogoutFragment extends Fragment {
    private Button btnLogout;
    private AccountRepository _accountRep;
    public LogoutFragment(){
    }
    public static LogoutFragment newInstance() {
        LogoutFragment fragment = new LogoutFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState){
        _accountRep = new AccountRepository(container.getContext());
        View view = inflater.inflate(R.layout.logout,container,false);
        btnLogout = view.findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login = new Intent(container.getContext(), MainActivity.class);
                _accountRep.Logout(login);
                MainActivity.email = "";
                startActivity(login);
            }
        });
        return  view;
    }

}
