package com.ubb.raiffeisen.raiffeisenbackendproject.Transaction;

import com.ubb.raiffeisen.raiffeisenbackendproject.CreditCard.CardNotFoundException;
import com.ubb.raiffeisen.raiffeisenbackendproject.CreditCard.CreditCard;
import com.ubb.raiffeisen.raiffeisenbackendproject.CreditCard.CreditCardJpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class TransactionController {

    private final TransactionJpaRepository transactionRepository;
    private final CreditCardJpaRepository cardJpaRepository;

    public TransactionController(TransactionJpaRepository transactionRepository, CreditCardJpaRepository cardJpaRepository) {
        this.transactionRepository = transactionRepository;
        this.cardJpaRepository = cardJpaRepository;
    }

    @GetMapping(path = "/transaction-list")
    public List<Transaction> getAllTransactions(){
        return transactionRepository.findAll();
    }

    @PostMapping(path = "/make-transaction/{card_id}")
    public Transaction makeTransaction(@RequestBody Transaction transaction, @PathVariable Long card_id){
        Optional<CreditCard> findCreditCard = cardJpaRepository.findById(card_id);
        if (findCreditCard.isEmpty()) throw new CardNotFoundException("Card with id: " + card_id + " does not exist!");

        transaction.setCreditCard(findCreditCard.get());
        CreditCard updateAmount = findCreditCard.get();
        updateAmount.setAmount((long) (updateAmount.getAmount() - transaction.getAmount()));
        cardJpaRepository.save(updateAmount);

        return transactionRepository.save(transaction);
    }

    @GetMapping(path = "/transaction/{transaction_id}")
    public ResponseEntity<Optional<Transaction>> showTransactionID(@PathVariable Long transaction_id){
        Optional<Transaction> checkTransaction = transactionRepository.findById(transaction_id);
        if (checkTransaction.isEmpty()) throw new TransactionNotFoundException("Transaction with id: " + transaction_id + " does not exist!");

        return ResponseEntity.ok(checkTransaction);
    }

    @GetMapping(path = "/transaction-sortH/{card_id}")
    public ResponseEntity<List<Transaction>> filterHighest(@PathVariable Long card_id) {
        Optional<CreditCard> optionalCreditCard = cardJpaRepository.findById(card_id);
        if (optionalCreditCard.isEmpty()) {
            throw new CardNotFoundException("Card with id: " + card_id + " does not exist!");
        }

        CreditCard creditCard = optionalCreditCard.get();

        List<Transaction> sortedTransactions = transactionRepository.findAllByCreditCard(creditCard).stream()
                .sorted(Comparator.comparing(Transaction::getAmount).reversed())
                .collect(Collectors.toList());

        return ResponseEntity.ok(sortedTransactions);
    }

    @GetMapping(path = "/transaction-sortL/{card_id}")
    public ResponseEntity<List<Transaction>> filterLowestTransaction(@PathVariable Long card_id) {
        Optional<CreditCard> optionalCreditCard = cardJpaRepository.findById(card_id);
        if (optionalCreditCard.isEmpty()) {
            throw new CardNotFoundException("Card with id: " + card_id + " does not exist!");
        }

        CreditCard creditCard = optionalCreditCard.get();

        List<Transaction> sortedTransactions = transactionRepository.findAllByCreditCard(creditCard).stream()
                .sorted(Comparator.comparing(Transaction::getAmount))
                .collect(Collectors.toList());

        return ResponseEntity.ok(sortedTransactions);
    }



}
