package com.ubb.raiffeisen.raiffeisenbackendproject.CreditCard;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class CardNotFoundException extends RuntimeException {
    public CardNotFoundException(String message) {
        super(message);
    }
}
