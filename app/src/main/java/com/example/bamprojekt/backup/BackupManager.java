package com.example.bamprojekt.backup;

import android.content.Context;
import android.os.Environment;
import com.example.bamprojekt.cryptography.CryptoService;
import com.example.bamprojekt.models.CreditCard;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

public class BackupManager {

    public static void exportCreditCardData(List<CreditCard> data, String fileName, String key,  Context context){
        try {
            File myExternalFile = new File(context.getExternalFilesDir(null), fileName);
            FileOutputStream outputStream = new FileOutputStream(myExternalFile);
            String content = createContent(data);
            byte[] encryptedContent = CryptoService.encryptWithOwnKey(content, key).getBytes();
            outputStream.write(encryptedContent);

        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isStorageAvailable() {
        String externalStorageState = Environment.getExternalStorageState();
        if (externalStorageState.equals(Environment.MEDIA_MOUNTED)){
            return true;
        }
        return false;
    }

    private static String createContent (List<CreditCard> data) {
        StringBuilder content = new StringBuilder("");
        for (CreditCard card : data){
            content.append(card.getName() + " "
                    + card.getCcv() + " "
                    + card.getNumber() + " "
                    + card.getValidDate() + " "
                    + card.getOwner() + "\n");
        }

        return content.toString();
    }
}
