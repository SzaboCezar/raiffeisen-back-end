package com.ubb.raiffeisen.raiffeisenbackendproject.Budget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class BudgetService {
    @Autowired
    private BudgetRepository budgetRepository;

    public List<Budget> getAllBudgets() {
        return budgetRepository.findAll();
    }

    public Budget createBudget(Budget budget) {
        return budgetRepository.save(budget);
    }

    public List<Budget> findBudgetsInDateRange(LocalDate startDate, LocalDate endDate) {
        return budgetRepository.findByStartDateAfterAndEndDateBefore(startDate, endDate);
    }

    public Budget updateBudget(Long id, Budget newBudgetData) {
        Optional<Budget> optionalBudget = budgetRepository.findById(id);
        if (optionalBudget.isEmpty()) {
            throw new BudgetNotFoundException("Budget with id: " + id + " does not exist!");
        }
        Budget existingBudget = optionalBudget.get();
        existingBudget.setTotalAmount(newBudgetData.getTotalAmount());
        existingBudget.setStartDate(newBudgetData.getStartDate());
        existingBudget.setEndDate(newBudgetData.getEndDate());

        return budgetRepository.save(existingBudget);
    }

    public void deleteBudget(Long id) {
        if (!budgetRepository.existsById(id)) {
            throw new BudgetNotFoundException("Budget with id: " + id + " does not exist!");
        }
        budgetRepository.deleteById(id);
    }

    public List<Budget> findBudgetsByUserAndDateRange(Long userId, LocalDate startDate, LocalDate endDate) {
        return budgetRepository.findByUserIdAndStartDateGreaterThanEqualAndEndDateLessThanEqual(userId, startDate, endDate);
    }

    public Budget partialUpdateBudget(Long id, Map<String, Object> updates) {
        Optional<Budget> optionalBudget = budgetRepository.findById(id);
        if (optionalBudget.isEmpty()) {
            throw new RuntimeException("Budget not found");
        }
        Budget budget = optionalBudget.get();
        if (updates.containsKey("totalAmount")) {
            budget.setTotalAmount((Double) updates.get("totalAmount"));
        }
        if (updates.containsKey("startDate")) {
            budget.setStartDate(LocalDate.parse((String) updates.get("startDate")));
        }
        if (updates.containsKey("endDate")) {
            budget.setEndDate(LocalDate.parse((String) updates.get("endDate")));
        }
        return budgetRepository.save(budget);
    }

}
