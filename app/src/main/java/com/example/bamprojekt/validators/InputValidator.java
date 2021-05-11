package com.example.bamprojekt.validators;

import com.example.bamprojekt.models.CreditCard;
import com.example.bamprojekt.models.User;

public class InputValidator {

    private static String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$";

    public static boolean validateUser(User user){
        if (user.getUsername().isEmpty() ||
                !user.getPassword().matches(passwordRegex) ) {
            return false;
        }
        return true;
    }
}
