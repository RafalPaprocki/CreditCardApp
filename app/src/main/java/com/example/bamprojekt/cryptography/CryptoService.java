package com.example.bamprojekt.cryptography;
import android.util.Base64;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

// code for this class base on https://stackoverflow.com/questions/41223937/how-can-i-encrypte-my-password-android-studio
public class CryptoService {
    private static final String ALGORITHM = "AES";
    private static final String KEY = "2JKfh667adfDEJ78" ; // for better security i should replace it later with other solution for retrieving Public_key, for instance using key_store

    public static String encrypt(String value) throws Exception {
        Key key = generateKey();
        String encryptedValue64 = encrypt(key, value);

        return encryptedValue64;
    }

    public static String decrypt(String value) throws Exception {
        Key key = generateKey();
        String decryptedValue = decrypt(key, value);

        return decryptedValue;
    }

    public static String encryptWithOwnKey(String value, String ownKey) throws Exception{
        Key key = generateKey(ownKey);
        String encryptedValue64 = encrypt(key, value);

        return encryptedValue64;
    }

    public static String decryptWithOwnKey(String value, String ownKey) throws Exception{
        Key key = generateKey(ownKey);
        String decryptedValue = decrypt(key, value);

        return decryptedValue;
    }

    private static Key generateKey() throws Exception {
        Key key = new SecretKeySpec(KEY.getBytes(),ALGORITHM);

        return key;
    }

    private static Key generateKey(String ownPublicKey) throws Exception {
        Key key = new SecretKeySpec(ownPublicKey.getBytes(),ALGORITHM);

        return key;
    }

    private static String decrypt(Key key, String value) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decryptedValue64 = Base64.decode(value, Base64.DEFAULT);
        byte [] decryptedByteValue = cipher.doFinal(decryptedValue64);
        String decryptedValue = new String(decryptedByteValue,"utf-8");

        return decryptedValue;
    }

    private static String encrypt(Key key, String value) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte [] encryptedByteValue = cipher.doFinal(value.getBytes("utf-8"));
        String encryptedValue64 = Base64.encodeToString(encryptedByteValue, Base64.DEFAULT);

        return encryptedValue64;
    }
}

