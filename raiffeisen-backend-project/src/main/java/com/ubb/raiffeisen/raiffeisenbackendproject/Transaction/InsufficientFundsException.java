package com.ubb.raiffeisen.raiffeisenbackendproject.Transaction;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)

public class InsufficientFundsException extends RuntimeException{
    private String message;
    public InsufficientFundsException(String message) {
        super(message);
    }
}
