package com.example.bamprojekt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.example.bamprojekt.autorization.LoginActivity;
import com.example.bamprojekt.autorization.Registration;
import com.example.bamprojekt.creditCard.CreditCardActivity;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences preferences = getSharedPreferences("autologin", MODE_PRIVATE);
        boolean userLogOn = preferences.getBoolean("rememberMe", false);
        if (userLogOn) {
            Intent intent;
            intent = new Intent(this, CreditCardActivity.class);
            startActivity(intent);
        } else {
            setContentView(R.layout.activity_main);
        }

    }

    public void onClickLogin(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void onClickRegister(View view) {
        Intent intent = new Intent(this, Registration.class);
        startActivity(intent);
    }
}