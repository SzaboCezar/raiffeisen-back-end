package com.ubb.raiffeisen.raiffeisenbackendproject.Transaction;

import com.ubb.raiffeisen.raiffeisenbackendproject.CreditCard.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionJpaRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findAllByCreditCard(CreditCard creditCard);

}
