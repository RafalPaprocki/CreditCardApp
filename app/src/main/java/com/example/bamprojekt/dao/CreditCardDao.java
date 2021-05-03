package com.example.bamprojekt.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.bamprojekt.models.CreditCard;

import java.util.List;

@Dao
public interface CreditCardDao {
    @Insert()
    void addCard(CreditCard card);

    @Update()
    void editCard(CreditCard card);

    @Query("Select * from creditCards")
    List<CreditCard> getAllCreditCards();

    @Query("Select name from creditCards")
    List<String> getNamesOfAllCreditCards();

    @Query("Select * from creditCards where id = (:cardId)")
    CreditCard getCreditCardDetails(int cardId);
}
