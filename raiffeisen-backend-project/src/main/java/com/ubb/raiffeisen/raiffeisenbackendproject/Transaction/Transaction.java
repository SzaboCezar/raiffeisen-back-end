package com.ubb.raiffeisen.raiffeisenbackendproject.Transaction;

import com.ubb.raiffeisen.raiffeisenbackendproject.CreditCard.CreditCard;
import com.ubb.raiffeisen.raiffeisenbackendproject.User.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "transaction_details")
public class Transaction {
    @Id
    @GeneratedValue
    private Long transactionID;
    private String type;
    private double amount;
    private LocalDate date;
    private String description;

    @ManyToOne
    private CreditCard creditCard;

}
