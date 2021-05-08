package com.example.bamprojekt.validators;

public class CreditCardExportValidator {
    private final String protectionKeyPattern = ".{16}";
    private String message;

    public boolean validateExportForm(String fileName, String protectionKey) {
        message = "";
        if (!protectionKey.matches(protectionKeyPattern)) {
            message = message + "- Invalidate protection key (should consist of 16 digits) \n";
        }
        if (fileName.isEmpty()){
            message = message + "- File name can't be empty \n";
        }

        if (message.isEmpty()){
            return true;
        } else{
            return false;
        }
    }

    public String getMessage() {
        return message;
    }

}
