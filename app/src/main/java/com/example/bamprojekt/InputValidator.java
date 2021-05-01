package com.example.bamprojekt;

public class InputValidator {
    public static boolean validateUser(User user){
        if (user.getUsername().isEmpty() ||
                user.getPassword().isEmpty() ) {
            return false;
        }
        return true;
    }
}
