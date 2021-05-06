package com.example.bamprojekt.creditCard;

import android.util.Log;

import com.example.bamprojekt.cryptography.CryptoService;
import com.example.bamprojekt.models.CreditCard;

public class CreditCardService {
    public static CreditCard encryptSensitiveData(CreditCard card) {
        try {
            card.setCcv( CryptoService.encrypt(card.getCcv()));
            card.setNumber( CryptoService.encrypt(card.getNumber()));
            card.setOwner( CryptoService.encrypt(card.getOwner()));
            card.setValidDate( CryptoService.encrypt(card.getValidDate()));
        } catch (Exception ex) {
            Log.d("Exception", ex.getMessage());
        }

        return card;
    }

    public static CreditCard decryptSensitiveData(CreditCard card) {
        try {
            card.setCcv(CryptoService.decrypt(card.getCcv()));
            card.setNumber(CryptoService.decrypt(card.getNumber()));
            card.setOwner(CryptoService.decrypt(card.getOwner()));
            card.setValidDate(CryptoService.decrypt(card.getValidDate()));
        } catch (Exception ex) {
            Log.d("Exception", ex.getMessage());
        }

        return card;
    }
}
