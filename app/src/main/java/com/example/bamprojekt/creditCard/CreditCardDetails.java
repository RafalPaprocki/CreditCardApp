package com.example.bamprojekt.creditCard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.bamprojekt.AppDatabase;
import com.example.bamprojekt.R;
import com.example.bamprojekt.cryptography.CryptoService;
import com.example.bamprojekt.dao.CreditCardDao;
import com.example.bamprojekt.models.CreditCard;

public class CreditCardDetails extends AppCompatActivity {
    private TextView detailsName;
    private TextView cardNumber;
    private TextView ccv;
    private TextView owner;
    private TextView validDate;
    private int cardId;
    private CreditCard cardDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit_card_details);
        initTextViews();
        setCardId();
        try {
            Thread cardDetailsThread = new Thread(() -> getCardDetails());
            cardDetailsThread.start();
            cardDetailsThread.join();
            setDetailViewInfo(cardDetails);
        } catch (Exception ex) {
            Log.d("Exception" , ex.getMessage());
        }
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
    }

    private void setCardId(){
        Intent intent = getIntent();
        cardId = intent.getIntExtra("cardId", -1);
    }

    private void initTextViews(){
        detailsName = findViewById(R.id.details_name);
        cardNumber = findViewById(R.id.details_number);
        ccv = findViewById(R.id.details_ccv);
        owner = findViewById(R.id.details_owner);
        validDate = findViewById(R.id.details_valid_date);
    }

    private void getCardDetails() {
        AppDatabase appDatabase = AppDatabase.getAppDatabase(getApplicationContext());
        CreditCardDao creditCardDao = appDatabase.creditCardDao();
        cardDetails = creditCardDao.getCreditCardDetails(cardId);
    }

    private void setDetailViewInfo(CreditCard cardDetails) {
        cardDetails = CreditCardService.decryptSensitiveData(cardDetails);
        detailsName.setText(detailsName.getText() + cardDetails.getName());
        cardNumber.setText(cardNumber.getText() + cardDetails.getNumber());
        ccv.setText(ccv.getText() + cardDetails.getCcv());
        owner.setText(owner.getText() + cardDetails.getOwner());
        validDate.setText(validDate.getText() + cardDetails.getValidDate());
    }
}