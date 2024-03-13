package com.ubb.raiffeisen.raiffeisenbackendproject.Expense;

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
public class ExpenseController {
    private final ExpenseJpaRepository expenseRepository;
    private final CreditCardJpaRepository cardJpaRepository;

    public ExpenseController(ExpenseJpaRepository expenseRepository, CreditCardJpaRepository cardJpaRepository) {
        this.expenseRepository = expenseRepository;
        this.cardJpaRepository = cardJpaRepository;
    }

    @GetMapping(path = "/expense-list")
    public List<Expense> getAllExpenses(){
        return expenseRepository.findAll();
    }

    @PostMapping(path = "/make-expense/{card_id}")
    public Expense makeExpense(@RequestBody Expense expense, @PathVariable Long card_id){
        Optional<CreditCard> findCreditCard = cardJpaRepository.findById(card_id);
        if (findCreditCard.isEmpty()) throw new CardNotFoundException("Card with id: " + card_id + " does not exist!");

        CreditCard updateAmount = findCreditCard.get();
        if(updateAmount.getAmount() < expense.getAmount()) throw new InsufficientFundsException("Insufficient Funds!");
        if(expense.getCategories().equals(Categories.ECO)){
            updateAmount.setPoints(updateAmount.getPoints() + 250);
        }
        expense.setCreditCard(findCreditCard.get());
        updateAmount.setAmount((long) (updateAmount.getAmount() - expense.getAmount()));
        cardJpaRepository.save(updateAmount);
        return expenseRepository.save(expense);
    }

    @GetMapping(path = "/expense/{expense_id}")
    public ResponseEntity<Optional<Expense>> showExpenseID(@PathVariable Long expense_id){
        Optional<Expense> checkExpense = expenseRepository.findById(expense_id);
        if (checkExpense.isEmpty()) throw new ExpenseNotFoundException("Expense with id: " + expense_id + " does not exist!");
        return ResponseEntity.ok(checkExpense);
    }

    @GetMapping(path = "/expense-sortH/{card_id}")
    public ResponseEntity<List<Expense>> filterHighest(@PathVariable Long card_id) {
        Optional<CreditCard> optionalCreditCard = cardJpaRepository.findById(card_id);
        if (optionalCreditCard.isEmpty()) {
            throw new CardNotFoundException("Card with id: " + card_id + " does not exist!");
        }

        CreditCard creditCard = optionalCreditCard.get();
        List<Expense> sortedExpenses = expenseRepository.findAllByCreditCard(creditCard).stream()
                .sorted(Comparator.comparing(Expense::getAmount).reversed())
                .collect(Collectors.toList());

        return ResponseEntity.ok(sortedExpenses);
    }

    @GetMapping(path = "/expense-sortL/{card_id}")
    public ResponseEntity<List<Expense>> filterLowestExpense(@PathVariable Long card_id) {
        Optional<CreditCard> optionalCreditCard = cardJpaRepository.findById(card_id);
        if (optionalCreditCard.isEmpty()) {
            throw new CardNotFoundException("Card with id: " + card_id + " does not exist!");
        }
        CreditCard creditCard = optionalCreditCard.get();

        List<Expense> sortedExpenses = expenseRepository.findAllByCreditCard(creditCard).stream()
                .sorted(Comparator.comparing(Expense::getAmount))
                .collect(Collectors.toList());

        return ResponseEntity.ok(sortedExpenses);
    }
}