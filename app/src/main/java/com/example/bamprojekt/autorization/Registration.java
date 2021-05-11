package com.example.bamprojekt.autorization;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bamprojekt.AppDatabase;
import com.example.bamprojekt.R;
import com.example.bamprojekt.models.User;
import com.example.bamprojekt.dao.UserDao;
import com.example.bamprojekt.validators.InputValidator;

import static com.example.bamprojekt.cryptography.HashGenerator.generateHash;
import static com.example.bamprojekt.validators.InputValidator.validateUser;

public class Registration extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
    }

    public void register(View view) {
        EditText username = (EditText) findViewById(R.id.username);
        EditText password = (EditText) findViewById(R.id.password);

        User newUser = new User();
        newUser.setPassword(password.getText().toString());
        newUser.setUsername(username.getText().toString());

        if (InputValidator.validateUser(newUser) == false){
            Toast.makeText(getApplicationContext(), "Bad input data (password should consist of at least 8 letter and at least one number and one uppercase letter)", Toast.LENGTH_LONG).show();
            return;
        }

        newUser.setPassword(generateHash(newUser.getPassword()));
        AppDatabase appDatabase = AppDatabase.getAppDatabase(getApplicationContext());
        UserDao userDao = appDatabase.userDao();
        new Thread(() -> registerUser(newUser, userDao))
                .start();
    }

    private void registerUser(User user, UserDao userDao) {
        if (isUsernameExist(user.getUsername(), userDao)) {
            runOnUiThread(() -> Toast.makeText(getApplicationContext(), "User already exist", Toast.LENGTH_SHORT).show());
            return;
        }
        userDao.registerUser(user);
        runOnUiThread(() -> Toast.makeText(getApplicationContext(), "User successfully registered, you can now log in", Toast.LENGTH_SHORT).show());
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    private boolean isUsernameExist(String username, UserDao userDao) {
        User user = userDao.getUserByUsername(username);

        return user != null;
    }
}