package com.ubb.raiffeisen.raiffeisenbackendproject.Budget;
import com.ubb.raiffeisen.raiffeisenbackendproject.User.User;
import com.ubb.raiffeisen.raiffeisenbackendproject.User.UserNotFoundException;
import com.ubb.raiffeisen.raiffeisenbackendproject.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/budgets")
public class BudgetController {
    private final BudgetService budgetService;
    private final UserService userService;

    public BudgetController(BudgetService budgetService, UserService userService) {
        this.budgetService = budgetService;
        this.userService = userService;
    }

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
        Optional<User> checkUser = userService.findUserById(userId);
        if (checkUser.isEmpty()) throw new UserNotFoundException("User with id: " + userId + " does not exist!");
        List<Budget> budgets = budgetService.findBudgetsByUserAndDateRange(checkUser.get(), startDate, endDate);
        return ResponseEntity.ok(budgets);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Budget> partialUpdateBudget(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        Budget updatedBudget = budgetService.partialUpdateBudget(id, updates);
        return ResponseEntity.ok(updatedBudget);
    }


}
