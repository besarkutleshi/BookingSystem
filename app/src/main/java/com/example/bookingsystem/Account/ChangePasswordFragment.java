package com.example.bookingsystem.Account;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.example.bookingsystem.Account.Model.AccountRepository;
import com.example.bookingsystem.Account.Model.ChangePasswordModel;
import com.example.bookingsystem.MainActivity;
import com.example.bookingsystem.R;

public class ChangePasswordFragment extends Fragment {
    private EditText txtOldpass;
    private EditText txtNewpass;
    private EditText txtConNewpass;
    private Button btnChange;
    private AccountRepository _accountRep;
    public ChangePasswordFragment(){

    }

    public static ChangePasswordFragment newInstance() {
        ChangePasswordFragment fragment = new ChangePasswordFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                         Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.activity_change_password,container,false);
        txtOldpass = view.findViewById(R.id.txtOldPassword);
        txtNewpass = view.findViewById(R.id.txtNewPassword);
        txtConNewpass = view.findViewById(R.id.txtConfrimPassword);
        btnChange = view.findViewById(R.id.btnChangePasword);

        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangePasswordModel model = new ChangePasswordModel(MainActivity.email,txtOldpass.getText().toString(),txtNewpass.getText().toString(),
                        txtConNewpass.getText().toString());
                _accountRep = new AccountRepository(getContext());
                _accountRep.ChangePassword(model);
            }
        });

        return view;

    }
}
