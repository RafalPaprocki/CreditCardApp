package com.example.bamprojekt.creditCard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bamprojekt.AppDatabase;
import com.example.bamprojekt.R;
import com.example.bamprojekt.cryptography.CryptoService;
import com.example.bamprojekt.dao.CreditCardDao;
import com.example.bamprojekt.models.CreditCard;
import com.example.bamprojekt.validators.CreditCardValidator;

public class CreditCardEdit extends AppCompatActivity {
    private CreditCardValidator cardValidator;
    private EditText number;
    private EditText ccv;
    private EditText owner;
    private EditText validDate;
    private int cardId;
    private CreditCard card;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit_card_edit);
        cardValidator = new CreditCardValidator();
        initTextViews();
        setCardId();
        try {
            Thread cardThread = new Thread(() -> getCard());
            cardThread.start();
            cardThread.join();
            setEditedValues();
        } catch (Exception ex) {
            Log.d("Exception" , ex.getMessage());
        }
    }

    public void saveEdited(View view) {
        card.setCcv(ccv.getText().toString());
        card.setNumber(number.getText().toString());
        card.setOwner(owner.getText().toString());
        card.setValidDate(validDate.getText().toString());

        if (cardValidator.validate(card) == false){
            Toast.makeText(getApplicationContext(), cardValidator.getMessage(), Toast.LENGTH_LONG).show();
            return;
        }

        try {
            card.setCcv( CryptoService.encrypt(card.getCcv()));
            card.setNumber( CryptoService.encrypt(card.getNumber()));
            card.setOwner( CryptoService.encrypt(card.getOwner()));
            card.setValidDate( CryptoService.encrypt(card.getValidDate()));
        } catch (Exception ex) {
            Log.d("Exception", ex.getMessage());
        }

        new Thread(() -> editCard(card))
                .start();
    }

    private void getCard() {
        AppDatabase appDatabase = AppDatabase.getAppDatabase(getApplicationContext());
        CreditCardDao creditCardDao = appDatabase.creditCardDao();
        card = creditCardDao.getCreditCardDetails(cardId);
    }

    private void initTextViews(){
        number = findViewById(R.id.edit_card_number);
        ccv = findViewById(R.id.edit_ccv);
        owner = findViewById(R.id.edit_owner);
        validDate = findViewById(R.id.edit_valid_date);
    }

    private void setCardId(){
        Intent intent = getIntent();
        cardId = intent.getIntExtra("cardId", -1);
    }

    private void editCard(CreditCard card) {
        AppDatabase appDatabase = AppDatabase.getAppDatabase(getApplicationContext());
        CreditCardDao creditCardDao = appDatabase.creditCardDao();
        creditCardDao.editCard(card);
        runOnUiThread(() -> Toast.makeText(getApplicationContext(), "Cart successfully updated :)", Toast.LENGTH_SHORT).show());
        Intent intent = new Intent(this, CreditCardActivity.class);
        startActivity(intent);
    }

    private void setEditedValues(){
        try {
            card.setCcv(CryptoService.decrypt(card.getCcv()));
            card.setNumber(CryptoService.decrypt(card.getNumber()));
            card.setOwner(CryptoService.decrypt(card.getOwner()));
            card.setValidDate(CryptoService.decrypt(card.getValidDate()));
        } catch (Exception ex) {
            Log.d("Error", ex.getMessage());
        }
        number.setText(card.getNumber());
        ccv.setText(card.getCcv());
        owner.setText(card.getOwner());
        validDate.setText(card.getValidDate());
    }
}