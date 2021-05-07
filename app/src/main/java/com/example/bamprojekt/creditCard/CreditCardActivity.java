package com.example.bamprojekt.creditCard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.bamprojekt.R;

public class CreditCardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit_card);
    }

    public void creditCardList(View view) {
        Intent intent = new Intent(CreditCardActivity.this, CreditCardList.class);

        /*
        ContentResolver cr = getContentResolver();
        Cursor cursor = cr.query(Uri.parse("content://com.provider.bamprojekt/cards"), null, null, null, null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                String str = ("\n" + cursor.getString(cursor.getColumnIndex("name")));
                Log.d("MainActivity", str);
                cursor.moveToNext();
            }

        } else {
            Log.d("MainActivity", "No records found");
        }
        */
        startActivity(intent);
    }

    public void addCreditCard(View view) {
        Intent intent = new Intent(CreditCardActivity.this, CreditCardCreate.class);
        startActivity(intent);
    }
}