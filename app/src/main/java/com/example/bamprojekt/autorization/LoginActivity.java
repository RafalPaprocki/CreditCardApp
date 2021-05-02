package com.example.bamprojekt.autorization;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bamprojekt.AppDatabase;
import com.example.bamprojekt.R;
import com.example.bamprojekt.models.User;
import com.example.bamprojekt.dao.UserDao;
import com.example.bamprojekt.creditCard.CreditCardActivity;

import static com.example.bamprojekt.cryptography.HashGenerator.generateHash;

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void loginUser(View view){
        EditText username = (EditText)findViewById(R.id.username);
        EditText password = (EditText)findViewById(R.id.password);
        String usernameString = username.getText().toString();
        String passwordString = generateHash(password.getText().toString());

        if (validateInput(usernameString, passwordString) == false){
            Toast.makeText(getApplicationContext(), "Bad input data", Toast.LENGTH_SHORT);
            return;
        }
        AppDatabase appDatabase = AppDatabase.getAppDatabase(getApplicationContext());
        UserDao userDao = appDatabase.userDao();
        new Thread(() -> loginUser(usernameString, passwordString, userDao)).start();

    }

    private boolean validateInput(String username, String password){
        if (username.isEmpty() ||
                password.isEmpty() ) {
            return false;
        }
        return true;
    }

    private void loginUser(String username, String password, UserDao userDao){
        User user = userDao.getUserByUsername(username);
        if(user == null || !user.getPassword().equals(password)) {
            runOnUiThread(() ->  Toast.makeText(getApplicationContext(),"Invalid username or password", Toast.LENGTH_SHORT).show());
            return;
        }

        runOnUiThread(() -> Toast.makeText(getApplicationContext(), "Successfully logged in", Toast.LENGTH_SHORT).show());
        startActivity(new Intent(LoginActivity.this, CreditCardActivity.class));
    }
}