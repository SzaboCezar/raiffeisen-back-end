package com.ubb.raiffeisen.raiffeisenbackendproject.Transaction;

public class InsufficientFundsException extends RuntimeException {
    public InsufficientFundsException(String message) {
        super(message);
    }
}
