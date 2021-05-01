package com.example.bamprojekt;

import java.security.MessageDigest;

import javax.crypto.spec.SecretKeySpec;

import kotlin.NotImplementedError;

public class Encoder {

    private String encrypt(String data, String password){
        // SecretKeySpec key = generateKey(password);
        //Cipher c = Cipher.getInstance();
        throw new NotImplementedError();
    }

    private SecretKeySpec generateKey(String password) throws Exception {
        final MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] bytes = password.getBytes("UTF-8");
        digest.update(bytes, 0, bytes.length);
        byte[] key = digest.digest();
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");

        return secretKeySpec;
    }
}
