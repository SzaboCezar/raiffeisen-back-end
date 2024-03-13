package com.ubb.raiffeisen.raiffeisenbackendproject.Expense;

import com.ubb.raiffeisen.raiffeisenbackendproject.CreditCard.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseJpaRepository extends JpaRepository<Expense, Long> {
    List<Expense> findAllByCreditCard(CreditCard creditCard);

}
