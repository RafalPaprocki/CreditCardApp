package com.example.bamprojekt.creditCard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bamprojekt.AppDatabase;
import com.example.bamprojekt.R;
import com.example.bamprojekt.autorization.LoginActivity;
import com.example.bamprojekt.cryptography.CryptoService;
import com.example.bamprojekt.dao.CreditCardDao;
import com.example.bamprojekt.dao.UserDao;
import com.example.bamprojekt.models.CreditCard;
import com.example.bamprojekt.validators.CreditCardValidator;

import static com.example.bamprojekt.cryptography.HashGenerator.generateHash;

public class CreditCardCreate extends AppCompatActivity {
    private CreditCardValidator cardValidator;
    private EditText number;
    private EditText owner;
    private EditText validDate;
    private EditText ccv;
    private EditText cardName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit_card_create);

        cardValidator = new CreditCardValidator();
        number = findViewById(R.id.number);
        owner = findViewById(R.id.owner);
        validDate = findViewById(R.id.validDate);
        ccv = findViewById(R.id.ccv);
        cardName = findViewById(R.id.card_name);
    }

    public void createCreditCard(View view) {
        CreditCard card = new CreditCard();
        card.setCcv(ccv.getText().toString());
        card.setNumber(number.getText().toString());
        card.setOwner(owner.getText().toString());
        card.setValidDate(validDate.getText().toString());
        card.setName(cardName.getText().toString());

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
        AppDatabase appDatabase = AppDatabase.getAppDatabase(getApplicationContext());
        CreditCardDao creditCardDao = appDatabase.creditCardDao();
        new Thread(() -> insertNewCard(card, creditCardDao))
                .start();
    }

    private void insertNewCard(CreditCard card, CreditCardDao cardDao) {
        cardDao.addCard(card);
        runOnUiThread(() -> Toast.makeText(getApplicationContext(), "Cart successfully added :)", Toast.LENGTH_SHORT).show());
        Intent intent = new Intent(this, CreditCardActivity.class);
        startActivity(intent);
    }
}