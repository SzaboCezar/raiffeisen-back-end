package com.ubb.raiffeisen.raiffeisenbackendproject.Income;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/incomes")
public class IncomeController {
    @Autowired
    private IncomeService incomeService;

    @GetMapping
    public List<Income> getAllIncomes() {
        return incomeService.getAllIncomes();
    }

    @PostMapping
    public Income createIncome(@RequestBody Income income) {
        return incomeService.createIncome(income);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Income>> getIncomesByCategory(@PathVariable Categories category) {
        List<Income> incomes = incomeService.findIncomesByCategory(category);
        return ResponseEntity.ok(incomes);
    }

    @GetMapping("/categories/{categories}")
    public ResponseEntity<List<Income>> getIncomesByCategories(@PathVariable String categories) {
        List<Income> incomes = new ArrayList<>();
        Arrays.stream(categories.split(","))
                .map(Categories::valueOf)
                .forEach(category -> incomes.addAll(incomeService.findIncomesByCategory(category)));
        return ResponseEntity.ok(incomes);
    }

    @GetMapping("/amount/{amount}")
    public ResponseEntity<List<Income>> getIncomesByAmount(@PathVariable Double amount) {
        List<Income> incomes = incomeService.findIncomesByAmount(amount);
        return ResponseEntity.ok(incomes);
    }

    @GetMapping("/description/{description}")
    public ResponseEntity<List<Income>> getIncomesByDescription(@PathVariable String description) {
        List<Income> incomes = incomeService.findIncomesByDescription(description);
        return ResponseEntity.ok(incomes);
    }

    @GetMapping("/amount/{amount}/description/{description}")
    public ResponseEntity<List<Income>> getIncomesByAmountAndDescription(@PathVariable Double amount, @PathVariable String description) {
        List<Income> incomes = incomeService.findIncomesByAmountAndDescription(amount, description);
        return ResponseEntity.ok(incomes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Income> getIncomeById(@PathVariable Long id) {
        Optional<Income> income = incomeService.getIncomeById(id);
        if (income.isPresent()) {
            return ResponseEntity.ok(income.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Income> updateIncome(@PathVariable Long id, @RequestBody Income incomeDetails) {
        try {
            Income existingIncome = incomeService.getIncomeById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Income not found"));
            incomeDetails.setId(existingIncome.getId());
            Income updatedIncome = incomeService.updateIncome(incomeDetails);
            return ResponseEntity.ok(updatedIncome);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIncome(@PathVariable Long id) {
        try {
            incomeService.deleteIncome(id);
            return ResponseEntity.noContent().build();
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/dateRange")
    public ResponseEntity<List<Income>> findIncomesByDateRange(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<Income> incomes = incomeService.findIncomesByDateRange(startDate, endDate);
        return ResponseEntity.ok(incomes);
    }

    @GetMapping("/user/{userId}/dateRange")
    public ResponseEntity<List<Income>> findIncomesByUserAndDateRange(@PathVariable Long userId, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<Income> incomes = incomeService.findIncomesByUserAndDateRange(userId, startDate, endDate);
        return ResponseEntity.ok(incomes);
    }

    @GetMapping("/user/{userId}/totalIncome")
    public ResponseEntity<Double> getTotalIncomeForUser(@PathVariable Long userId) {
        double totalIncome = incomeService.getTotalIncomeForUser(userId);
        return ResponseEntity.ok(totalIncome);
    }

    @GetMapping("/user/{userId}/averageIncome")
    public ResponseEntity<Double> getAverageIncomeForUser(@PathVariable Long userId) {
        double averageIncome = incomeService.getAverageIncomeForUser(userId);
        return ResponseEntity.ok(averageIncome);
    }

}
