package com.ubb.raiffeisen.raiffeisenbackendproject.Transaction;

import com.ubb.raiffeisen.raiffeisenbackendproject.CreditCard.CreditCard;
import com.ubb.raiffeisen.raiffeisenbackendproject.User.User;

import java.time.LocalDate;

public class Transaction {
    private Long id;
    private String type;
    private double amount;
    private LocalDate date;
    private String description;
    private User user;
    private CreditCard creditCard;

    public Transaction(Long id, String type, double amount, LocalDate date, String description) {
        this.id = id;
        this.type = type;
        this.amount = amount;
        this.date = date;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", amount=" + amount +
                ", date=" + date +
                ", description='" + description + '\'' +
                ", user=" + user +
                ", creditCard=" + creditCard +
                '}';
    }
}
