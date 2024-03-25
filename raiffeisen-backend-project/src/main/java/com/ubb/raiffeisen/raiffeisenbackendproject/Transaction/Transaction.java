//package com.ubb.raiffeisen.raiffeisenbackendproject.Transaction;
//
//import com.ubb.raiffeisen.raiffeisenbackendproject.User.User;
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.math.BigDecimal;
//import java.time.LocalDate;
//
//@Data
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
//@Entity(name = "transaction_details")
//public class Transaction {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long transactionID;
//
//    private BigDecimal amount;
//    private LocalDate date;
//    private TransactionType type;
//
//    @ManyToOne
//    private User user;
//
//}
