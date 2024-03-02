package com.ubb.raiffeisen.raiffeisenbackendproject.Transaction;

import com.ubb.raiffeisen.raiffeisenbackendproject.CreditCard.CreditCard;
import com.ubb.raiffeisen.raiffeisenbackendproject.User.User;
import jakarta.persistence.*;
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

    @Enumerated(EnumType.STRING)
    private Categories categories;
    private double amount;
    private LocalDate date;
    private String description;

    @ManyToOne
    private CreditCard creditCard;

}
