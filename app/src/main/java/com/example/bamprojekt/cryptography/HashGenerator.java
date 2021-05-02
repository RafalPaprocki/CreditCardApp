package com.example.bamprojekt.cryptography;

import android.util.Log;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashGenerator {
    public static String generateHash(String hashingEntry) {
        String hashedString = null;
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] hashedPassword = md.digest(hashingEntry.getBytes(StandardCharsets.UTF_8));
            hashedString = new String(hashedPassword, StandardCharsets.UTF_8);

        } catch(NoSuchAlgorithmException ex){
            Log.d("Exception", "Bad name of the algorithm " + ex.getStackTrace());
        }

        return hashedString;
    }
}
