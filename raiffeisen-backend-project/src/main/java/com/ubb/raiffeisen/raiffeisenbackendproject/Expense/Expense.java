package com.ubb.raiffeisen.raiffeisenbackendproject.Expense;

import com.ubb.raiffeisen.raiffeisenbackendproject.CreditCard.CreditCard;
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
@Entity(name = "expense_details")
public class Expense {
    @Id
    @GeneratedValue
    private Long expenseID;

    @Enumerated(EnumType.STRING)
    private Categories categories;
    private double amount;
    private LocalDate date;
    private String description;

    @ManyToOne
    private CreditCard creditCard;

}
