package com.example.bamprojekt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.bamprojekt.HashGenerator.generateHash;
import static com.example.bamprojekt.InputValidator.validateUser;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void registerUser(View view) {
        EditText username = (EditText) findViewById(R.id.username);
        EditText password = (EditText) findViewById(R.id.password);

        User newUser = new User();
        String hashedPassword = generateHash(password.getText().toString());
        newUser.setPassword(hashedPassword);
        newUser.setUsername(username.getText().toString());

        if (validateUser((newUser)) == false){
            Toast.makeText(getApplicationContext(), "All fields must be filled!!!", Toast.LENGTH_SHORT).show();
            return;
        }

        AppDatabase appDatabase = AppDatabase.getAppDatabase(getApplicationContext());
        UserDao userDao = appDatabase.userDao();
        new Thread(() ->insertUser(newUser, userDao))
                .start();
    }

    private void insertUser(User user, UserDao userDao){
        userDao.registerUser(user);
        runOnUiThread(() ->  Toast.makeText(getApplicationContext(),"User successfully registered, you can now log in", Toast.LENGTH_SHORT).show());
    }
}