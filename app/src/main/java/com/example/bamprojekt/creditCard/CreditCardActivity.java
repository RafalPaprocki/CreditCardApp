package com.example.bamprojekt.creditCard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.bamprojekt.R;
import com.example.bamprojekt.autorization.LoginActivity;

public class CreditCardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit_card);
    }

    public void creditCardList(View view) {
        Intent intent = new Intent(CreditCardActivity.this, CreditCardList.class);
        startActivity(intent);
    }

    public void addCreditCard(View view) {
        Intent intent = new Intent(CreditCardActivity.this, CreditCardCreate.class);
        startActivity(intent);
    }
}