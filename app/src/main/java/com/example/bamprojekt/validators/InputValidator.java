package com.example.bamprojekt.validators;

import com.example.bamprojekt.models.CreditCard;
import com.example.bamprojekt.models.User;

public class InputValidator {
    public static boolean validateUser(User user){
        if (user.getUsername().isEmpty() ||
                user.getPassword().isEmpty() ) {
            return false;
        }
        return true;
    }

    public static boolean validateInput(String username, String password){
        if (username.isEmpty() ||
                password.isEmpty() ) {
            return false;
        }
        return true;
    }

}
