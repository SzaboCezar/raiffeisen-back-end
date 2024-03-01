package com.ubb.raiffeisen.raiffeisenbackendproject.Transaction;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionJpaRepository extends JpaRepository<Transaction, Long> {
}
