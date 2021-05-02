package com.example.bamprojekt.validators;

import com.example.bamprojekt.models.CreditCard;

public class CreditCardValidator {
    private String message;
    private final String numberPattern = "[\\d]{16}";
    private final String validDatePattern = "[0-2]{1}[0-9]{1}/[0-9]{2}";
    private final String ccvPattern = "[0-9]{3}";

    public boolean validate(CreditCard card){
        message = "";
        if (!card.getNumber().matches(numberPattern)) {
            message = message + "- Invalidate number (should consist of 16 digits) \n";
        }
        if (!card.getValidDate().matches(validDatePattern)) {
            message = message + "- Invalid valid date (should match pattern mm/yy e.g 06/23 \n";
        }
        if (!card.getCcv().matches(ccvPattern)) {
            message = message + "- Invalid ccv (should consist of 3 digits) \n";
        }
        if (card.getOwner().isEmpty()){
            message = message + "- Owner name can't be empty \n";
        }
        if (card.getName().isEmpty()){
            message = message + "- Name can't be empty \n";
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
