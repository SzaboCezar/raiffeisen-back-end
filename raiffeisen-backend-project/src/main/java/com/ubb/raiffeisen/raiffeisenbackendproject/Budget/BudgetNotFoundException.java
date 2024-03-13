package com.ubb.raiffeisen.raiffeisenbackendproject.Budget;

public class BudgetNotFoundException extends RuntimeException {
    public BudgetNotFoundException(String message) {
        super(message);
    }
}