package com.example.bamprojekt.creditCard;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bamprojekt.AppDatabase;
import com.example.bamprojekt.R;
import com.example.bamprojekt.backup.BackupManager;
import com.example.bamprojekt.dao.CreditCardDao;
import com.example.bamprojekt.models.CreditCard;
import com.example.bamprojekt.validators.CreditCardExportValidator;
import com.example.bamprojekt.validators.CreditCardValidator;

import java.util.List;

public class CreditCardExport extends AppCompatActivity {
    private List<CreditCard> dataToExport;
    private EditText fileName;
    private EditText key;
    private CreditCardExportValidator exportInputValidator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit_card_export);

        exportInputValidator = new CreditCardExportValidator();
        fileName =  findViewById(R.id.export_file_name);
        key =  findViewById(R.id.export_key);
    }

    public void exportData(View view) {
        getCreditCardData();
        String fileNameString = fileName.getText().toString();
        String keyString = key.getText().toString();

        if (exportInputValidator.validateExportForm(fileNameString, keyString) == false){
            Toast.makeText(getApplicationContext(), exportInputValidator.getMessage(), Toast.LENGTH_LONG).show();
            return;
        }
        if (BackupManager.isStorageAvailable() == false){
            Toast.makeText(getApplicationContext(), "Can export file because storage isn't available", Toast.LENGTH_LONG).show();
            return;
        }
        BackupManager.exportCreditCardData(dataToExport, fileNameString, keyString, getApplicationContext());
        Toast.makeText(getApplicationContext(), "Data exported to " + getExternalFilesDir(null).getPath(), Toast.LENGTH_SHORT).show();
    }

    private void getCreditCardData() {
        Thread getCreditCardThread = new Thread(() -> {
            AppDatabase appDatabase = AppDatabase.getAppDatabase(getApplicationContext());
            CreditCardDao creditCardDao = appDatabase.creditCardDao();
            List<CreditCard> creditCards = creditCardDao.getAllCreditCards();
            dataToExport = creditCards;
        });

        try {
            getCreditCardThread.start();
            getCreditCardThread.join();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}