package com.ubb.raiffeisen.raiffeisenbackendproject.Income;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class IncomeService {
    @Autowired
    private IncomeRepository incomeRepository;

    public List<Income> getAllIncomes() {
        List<Income> incomes = incomeRepository.findAll();
        if (incomes.isEmpty()) {
            throw new IncomeNotFoundException("No incomes found");
        }
        return incomes;
    }

    public Optional<Income> getIncomeById(Long id) {
        Optional<Income> income = incomeRepository.findById(id);
        if (income.isEmpty()) {
            throw new IncomeNotFoundException("Income with id: " + id + " does not exist!");
        }
        return income;
    }

    public Income createIncome(Income income) {
        if (income == null) {
            throw new IllegalArgumentException("Income cannot be null");
        }
        return incomeRepository.save(income);
    }

    public Income updateIncome(Income income) {
        if (!incomeRepository.existsById(income.getId())) {
            throw new IncomeNotFoundException("Income with id: " + income.getId() + " does not exist!");
        }
        return incomeRepository.save(income);
    }

    public void deleteIncome(Long id) {
        if (!incomeRepository.existsById(id)) {
            throw new IncomeNotFoundException("Income with id: " + id + " does not exist!");
        }
        incomeRepository.deleteById(id);
    }

    public List<Income> findIncomesByCategory(Categories incomeCategories) {
        List<Income> incomes = incomeRepository.findByIncomeCategories(incomeCategories);
        if (incomes.isEmpty()) {
            throw new IncomeNotFoundException("No incomes found for category: " + incomeCategories);
        }
        return incomes;
    }

    public List<Income> findIncomesByDateRange(LocalDate startDate, LocalDate endDate) {
        List<Income> incomes = incomeRepository.findByDateBetween(startDate, endDate);
        if (incomes.isEmpty()) {
            throw new IncomeNotFoundException("No incomes found between dates: " + startDate + " and " + endDate);
        }
        return incomes;
    }

    public List<Income> findIncomesByUserAndDateRange(Long userId, LocalDate startDate, LocalDate endDate) {
        List<Income> incomes = incomeRepository.findByUserUserIDAndDateBetween(userId, startDate, endDate);
        if (incomes.isEmpty()) {
            throw new IncomeNotFoundException("No incomes found for user with id: " + userId + " between dates: " + startDate + " and " + endDate);
        }
        return incomes;
    }

    public List<Income> findIncomesByAmount(Double amount) {
        List<Income> incomes = incomeRepository.findByAmount(amount);
        if (incomes.isEmpty()) {
            throw new IncomeNotFoundException("No incomes found with amount: " + amount);
        }
        return incomes;
    }

    public List<Income> findIncomesByDescription(String description) {
        List<Income> incomes = incomeRepository.findByDescription(description);
        if (incomes.isEmpty()) {
            throw new IncomeNotFoundException("No incomes found with description: " + description);
        }
        return incomes;
    }

    public List<Income> findIncomesByAmountAndDescription(Double amount, String description) {
        List<Income> incomes = incomeRepository.findByAmountAndDescription(amount, description);
        if (incomes.isEmpty()) {
            throw new IncomeNotFoundException("No incomes found with amount: " + amount + " and description: " + description);
        }
        return incomes;
    }

    public double getTotalIncomeForUser(Long userId) {
        List<Income> incomes = incomeRepository.findByUserUserID(userId);
        if (incomes == null || incomes.isEmpty()) {
            throw new IncomeNotFoundException("No incomes found for user with id: " + userId);
        }
        return incomes.stream()
                .mapToDouble(Income::getAmount)
                .sum();
    }

    public double getAverageIncomeForUser(Long userId) {
        List<Income> incomes = incomeRepository.findByUserUserID(userId);
        if (incomes == null || incomes.isEmpty()) {
            throw new IncomeNotFoundException("No incomes found for user with id: " + userId);
        }
        return incomes.stream()
                .mapToDouble(Income::getAmount)
                .average()
                .orElse(0.0);
    }
}
