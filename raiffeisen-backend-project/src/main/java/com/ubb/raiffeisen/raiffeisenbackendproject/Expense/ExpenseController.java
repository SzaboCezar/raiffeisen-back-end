package com.ubb.raiffeisen.raiffeisenbackendproject.Expense;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
public class ExpenseController {
    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping(path = "/expense-list")
    public List<Expense> getAllExpenses() {
        return expenseService.getExpenseList();
    }

    @PostMapping(path = "/make-expense/{card_id}")
    public Expense makeExpense(@RequestBody Expense expense, @PathVariable Long card_id) {
        return expenseService.makeExpense(expense, card_id);
    }

    @GetMapping(path = "/expense/{expense_id}")
    public ResponseEntity<Optional<Expense>> showExpenseID(@PathVariable Long expense_id) {
        return expenseService.showExpenseById(expense_id);
    }

    @GetMapping(path = "/expense-sortH/{card_id}")
    public ResponseEntity<List<Expense>> filterHighest(@PathVariable Long card_id) {
        return expenseService.filterHighestExpenses(card_id);
    }

    @GetMapping(path = "/expense-sortL/{card_id}")
    public ResponseEntity<List<Expense>> filterLowestExpense(@PathVariable Long card_id) {
        return expenseService.filterLowestExpenses(card_id);
    }
}