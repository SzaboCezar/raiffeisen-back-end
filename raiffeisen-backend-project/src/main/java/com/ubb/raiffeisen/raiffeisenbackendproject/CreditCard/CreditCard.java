package com.ubb.raiffeisen.raiffeisenbackendproject.CreditCard;

import com.ubb.raiffeisen.raiffeisenbackendproject.User.User;

import java.time.LocalDate;

public class CreditCard {
    private Long id;
    private String cardNumber;
    private LocalDate expirationDate;
    private String CVV;
    private Long amount;
    private Integer points;
    private User user;

    public CreditCard(Long id, String cardNumber, LocalDate expirationDate, String CVV, Long amount, Integer points, User user) {
        this.id = id;
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.CVV = CVV;
        this.amount = amount;
        this.points = points;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getCVV() {
        return CVV;
    }

    public void setCVV(String CVV) {
        this.CVV = CVV;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "CreditCard{" +
                "id=" + id +
                ", cardNumber='" + cardNumber + '\'' +
                ", expirationDate=" + expirationDate +
                ", CVV='" + CVV + '\'' +
                ", amount=" + amount +
                ", points=" + points +
                ", user=" + user +
                '}';
    }
}
