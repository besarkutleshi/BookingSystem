package com.example.bookingsystem.Account;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bookingsystem.HelperClass;
import com.example.bookingsystem.Interface.IAccountAPI;
import com.example.bookingsystem.MainActivity;
import com.example.bookingsystem.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterUser extends AppCompatActivity {
    private Button btnRegister;
    private Button btnClose;
    private EditText txtusername;
    private EditText txtpassword;
    private EditText txtconfirmpassword;
    private IAccountAPI accountAPI;
    private RegisterModel model;
    private AccountRepository _accountRep;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        btnRegister = findViewById(R.id.btnRegisterUser);
        btnClose = findViewById(R.id.btnClose);
        txtusername = findViewById(R.id.txtusernameR);
        txtpassword = findViewById(R.id.txtpasswordR);
        txtconfirmpassword = findViewById(R.id.txtconfirmpasswordR);
        final Intent i = new Intent(this, MainActivity.class);
        _accountRep = new AccountRepository(RegisterUser.this);
        accountAPI = HelperClass.GetRetrofit().create(IAccountAPI.class);

        btnRegister.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        model = new RegisterModel(txtusername.getText().toString(),txtpassword.getText().toString(),txtconfirmpassword.getText().toString());
                        _accountRep.Register(model,i);
                        txtpassword.setText(""); txtconfirmpassword.setText("");txtusername.setText("");
                    }
                }
        );

        btnClose.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        OpenActivity(i);
                    }
                }
        );
    }

    public void OpenActivity(Intent i){
        startActivity(i);
    }



}
