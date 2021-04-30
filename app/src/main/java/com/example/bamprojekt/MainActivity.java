package com.example.bamprojekt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void registerUser(View view) {
        EditText username = (EditText) findViewById(R.id.username);
        EditText password = (EditText) findViewById(R.id.password);

        User newUser = new User();
        newUser.setPassword(password.getText().toString());
        newUser.setUsername(username.getText().toString());

        if (validateInput((newUser)) == false){
            Toast.makeText(getApplicationContext(), "All fields must be filled!!!", Toast.LENGTH_SHORT).show();
        }

        AppDatabase appDatabase = AppDatabase.getAppDatabase(getApplicationContext());
        UserDao userDao = appDatabase.userDao();
        new Thread(() ->insertUser(newUser, userDao))
                .start();
    }

    public void loginUser(View view) {

    }

    private boolean validateInput(User user){
        if (user.getUsername().isEmpty() ||
            user.getPassword().isEmpty() ) {
            return false;
        }
        return true;
    }

    private void insertUser(User user, UserDao userDao){
        userDao.registerUser(user);
        runOnUiThread(() ->  Toast.makeText(getApplicationContext(),"User successfully registered, you can now log in", Toast.LENGTH_SHORT).show());
    }
}