package com.ubb.raiffeisen.raiffeisenbackendproject.Transaction;

public class TransactionNotFoundException extends RuntimeException {
    public TransactionNotFoundException(String message) {
        super(message);
    }
}
