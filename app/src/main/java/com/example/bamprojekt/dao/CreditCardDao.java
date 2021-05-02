package com.example.bamprojekt.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.bamprojekt.models.CreditCard;
import com.example.bamprojekt.models.User;

import java.util.List;

@Dao
public interface CreditCardDao {
    @Insert()
    void addCard(CreditCard card);

    @Query("Select * from creditCards")
    List<CreditCard> getAllCreditCards();
}
