package com.example.bamprojekt.creditCard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import com.example.bamprojekt.R;

public class CreditCardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit_card);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
    }

    public void creditCardList(View view) {
        Intent intent = new Intent(CreditCardActivity.this, CreditCardList.class);
        startActivity(intent);
    }

    public void addCreditCard(View view) {
        Intent intent = new Intent(CreditCardActivity.this, CreditCardCreate.class);
        startActivity(intent);
    }

    public void exportCreditCard(View view) {
        Intent intent = new Intent(CreditCardActivity.this, CreditCardExport.class);
        startActivity(intent);
    }
}