package com.example.bamprojekt.creditCard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;

import com.example.bamprojekt.AppDatabase;
import com.example.bamprojekt.R;
import com.example.bamprojekt.dao.CreditCardDao;
import com.example.bamprojekt.models.CreditCard;
import com.example.bamprojekt.viewAdapters.CreditCardAdapter;

import java.util.ArrayList;
import java.util.List;

public class CreditCardList extends AppCompatActivity {

    List<CreditCard> cardNamesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit_card_list);
        try {
            displayListOfCards();
        } catch(Exception e){
            Log.d("Exception", e.getMessage());
        }
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
    }

    private void getCardList() {
        AppDatabase appDatabase = AppDatabase.getAppDatabase(getApplicationContext());
        CreditCardDao creditCardDao = appDatabase.creditCardDao();
        List<CreditCard> names = creditCardDao.getAllCreditCards();
        cardNamesList = names;
    }

    private void displayListOfCards() throws Exception {
        Thread getDataThread = new Thread(() -> getCardList());
        getDataThread.start();
        getDataThread.join();

        CreditCardAdapter adapter = new CreditCardAdapter(this, cardNamesList);
        adapter.setOnDelete(this::deleteCard);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    public void deleteCard(int cardId) {
        AppDatabase appDatabase = AppDatabase.getAppDatabase(getApplicationContext());
        CreditCardDao creditCardDao = appDatabase.creditCardDao();
        new Thread(() -> creditCardDao.deleteByCardId(cardId))
                .start();
    }
}