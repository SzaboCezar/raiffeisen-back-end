package com.ubb.raiffeisen.raiffeisenbackendproject.Expense;

import com.ubb.raiffeisen.raiffeisenbackendproject.CreditCard.CardNotFoundException;
import com.ubb.raiffeisen.raiffeisenbackendproject.CreditCard.CreditCard;
import com.ubb.raiffeisen.raiffeisenbackendproject.CreditCard.CreditCardJpaRepository;
import com.ubb.raiffeisen.raiffeisenbackendproject.CreditCard.CreditCardService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ExpenseService {

    private final ExpenseJpaRepository expenseJpaRepository;
    private final CreditCardService cardService;
    private final CreditCardJpaRepository cardJpaRepository;

    public ExpenseService(ExpenseJpaRepository expenseJpaRepository, CreditCardService cardService, CreditCardJpaRepository cardJpaRepository) {
        this.expenseJpaRepository = expenseJpaRepository;
        this.cardService = cardService;
        this.cardJpaRepository = cardJpaRepository;
    }

    public List<Expense> getExpenseList(){
        return expenseJpaRepository.findAll();
    }

    public Expense makeExpense(Expense expense, Long card_id) {
        Optional<CreditCard> findCreditCard = cardService.findCreditCardById(card_id);
        if (findCreditCard.isEmpty()) {
            throw new CardNotFoundException("Card with id: " + card_id + " does not exist!");
        }

        CreditCard updateAmount = findCreditCard.get();
        if (updateAmount.getAmount() < expense.getAmount()) {
            throw new InsufficientFundsException("Insufficient Funds!");
        }
        if (expense.getCategories().equals(Categories.ECO)) {
            updateAmount.setPoints(updateAmount.getPoints() + 250);
        }
        expense.setCreditCard(findCreditCard.get());
        updateAmount.setAmount((long) (updateAmount.getAmount() - expense.getAmount()));
        cardJpaRepository.save(updateAmount);
        expense.setDate(LocalDate.now());
        return expenseJpaRepository.save(expense);
    }


    public ResponseEntity<Optional<Expense>> showExpenseById(Long expenseId) {
        Optional<Expense> checkExpense = expenseJpaRepository.findById(expenseId);
        if (checkExpense.isEmpty()) {
            throw new ExpenseNotFoundException("Expense with id: " + expenseId + " does not exist!");
        }
        return ResponseEntity.ok(checkExpense);
    }

    public ResponseEntity<List<Expense>> filterHighestExpenses(Long cardId) {
        Optional<CreditCard> optionalCreditCard = cardJpaRepository.findById(cardId);
        if (optionalCreditCard.isEmpty()) {
            throw new CardNotFoundException("Card with id: " + cardId + " does not exist!");
        }

        CreditCard creditCard = optionalCreditCard.get();
        List<Expense> sortedExpenses = expenseJpaRepository.findAllByCreditCard(creditCard).stream()
                .sorted(Comparator.comparing(Expense::getAmount).reversed())
                .collect(Collectors.toList());

        return ResponseEntity.ok(sortedExpenses);
    }

    public ResponseEntity<List<Expense>> filterLowestExpenses(Long cardId) {
        Optional<CreditCard> optionalCreditCard = cardJpaRepository.findById(cardId);
        if (optionalCreditCard.isEmpty()) {
            throw new CardNotFoundException("Card with id: " + cardId + " does not exist!");
        }

        CreditCard creditCard = optionalCreditCard.get();
        List<Expense> sortedExpenses = expenseJpaRepository.findAllByCreditCard(creditCard).stream()
                .sorted(Comparator.comparing(Expense::getAmount))
                .collect(Collectors.toList());

        return ResponseEntity.ok(sortedExpenses);
    }
}
