package com.example.bamprojekt.creditCard;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.bamprojekt.AppDatabase;
import com.example.bamprojekt.dao.CreditCardDao;
import com.example.bamprojekt.models.CreditCard;

public class CreditCardDelete {
    public static void deleteCard(int cardId, Context context) {
        AppDatabase appDatabase = AppDatabase.getAppDatabase(context);
        CreditCardDao creditCardDao = appDatabase.creditCardDao();
        new Thread(() -> creditCardDao.deleteByCardId(cardId))
                .start();
    }
}
