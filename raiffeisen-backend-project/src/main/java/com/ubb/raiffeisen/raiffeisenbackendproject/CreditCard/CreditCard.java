package com.ubb.raiffeisen.raiffeisenbackendproject.CreditCard;

import com.ubb.raiffeisen.raiffeisenbackendproject.User.User;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity(name = "credit_cards")
public class CreditCard {
    @Id
    @GeneratedValue
    private Long creditCardID;
    private String cardNumber;
    private LocalDate expirationDate;
    private String CVV;
    private Long amount;
    private Integer points;
    @OneToOne
    private User user;

    public CreditCard(Long creditCardID, String cardNumber, LocalDate expirationDate, String CVV, Long amount, Integer points) {
        this.creditCardID = creditCardID;
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.CVV = CVV;
        this.amount = amount;
        this.points = points;
    }

    public CreditCard() {

    }

    public Long getCreditCardID() {
        return creditCardID;
    }

    public void setCreditCardID(Long creditCardID) {
        this.creditCardID = creditCardID;
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
                "creditCardID=" + creditCardID +
                ", cardNumber='" + cardNumber + '\'' +
                ", expirationDate=" + expirationDate +
                ", CVV='" + CVV + '\'' +
                ", amount=" + amount +
                ", points=" + points +
                '}';
    }
}
