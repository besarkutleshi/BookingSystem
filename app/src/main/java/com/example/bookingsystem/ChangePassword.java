package com.example.bookingsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChangePassword extends AppCompatActivity {
    BottomNavigationView bottomNavigation;
    Intent favorites;
    Intent home;
    Button BtnChange;
    IAccountAPI accountAPI;
    EditText txtold,txtnew,txtconnew;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(nav);
        bottomNavigation.getMenu().findItem(R.id.navigation_change).setChecked(true);
        BtnChange = findViewById(R.id.btnChangePasword);
        accountAPI = HelperClass.GetRetrofit().create(IAccountAPI.class);
        txtold = findViewById(R.id.txtOldPassword);
        txtnew = findViewById(R.id.txtNewPassword);
        txtconnew = findViewById(R.id.txtConfrimPassword);
        BtnChange.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String newp,conp;
                        newp = txtnew.getText().toString(); conp = txtconnew.getText().toString();
                        if(newp.equals(conp)){
                            ChangePasswordModel model = new ChangePasswordModel(MainActivity.email,txtold.getText().toString(),txtnew.getText().toString(),
                                    txtconnew.getText().toString());
                            ChangePassword(model);
                            Toast.makeText(ChangePassword.this, "Password changed", Toast.LENGTH_SHORT).show();
                            txtnew.setText("");txtconnew.setText("");txtold.setText("");
                        }
                        else{
                            Toast.makeText(ChangePassword.this, "Password and confirm password do not match", Toast.LENGTH_SHORT).show();
                            txtnew.setText("");txtconnew.setText("");
                        }
                    }
                }
        );
    }


    public void ChangePassword(ChangePasswordModel model){
        Call<ChangePasswordModel> call = accountAPI.ChangePassword(model);
        call.enqueue(new Callback<ChangePasswordModel>() {
            @Override
            public void onResponse(Call<ChangePasswordModel> call, Response<ChangePasswordModel> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(ChangePassword.this, "Not Change \n" + response.message(), Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(ChangePassword.this, "Change Successful", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ChangePasswordModel> call, Throwable t) {
                Toast.makeText(ChangePassword.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    BottomNavigationView.OnNavigationItemSelectedListener nav = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.navigation_favorites:
                    startActivity(new Intent(ChangePassword.this,Favorites.class));
                    return  true;
                case R.id.navigation_home:
                    startActivity(new Intent(ChangePassword.this,Trips.class));
                    return true;
                case R.id.navigation_logout:
                    HelperClass.Logout();
                    startActivity(new Intent(ChangePassword.this,MainActivity.class));
                    return  true;
            }
            return false;
        }
    };
}
