package com.ubb.raiffeisen.raiffeisenbackendproject.Budget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/budgets")
public class BudgetController {
    @Autowired
    private BudgetService budgetService;

    @GetMapping
    public List<Budget> getAllBudgets() {
        return budgetService.getAllBudgets();
    }

    @PostMapping
    public Budget createBudget(@RequestBody Budget budget) {
        return budgetService.createBudget(budget);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Budget> updateBudget(@PathVariable Long id, @RequestBody Budget newBudgetData) {
        Budget updatedBudget = budgetService.updateBudget(id, newBudgetData);
        return ResponseEntity.ok(updatedBudget);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBudget(@PathVariable Long id) {
        budgetService.deleteBudget(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/dateRange")
    public List<Budget> getBudgetsInDateRange(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
        return budgetService.findBudgetsInDateRange(startDate, endDate);
    }

    @GetMapping("/user/{userId}/dateRange")
    public ResponseEntity<List<Budget>> getBudgetsByUserAndDateRange(@PathVariable Long userId, @RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
        List<Budget> budgets = budgetService.findBudgetsByUserAndDateRange(userId, startDate, endDate);
        return ResponseEntity.ok(budgets);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Budget> partialUpdateBudget(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        Budget updatedBudget = budgetService.partialUpdateBudget(id, updates);
        return ResponseEntity.ok(updatedBudget);
    }


}
