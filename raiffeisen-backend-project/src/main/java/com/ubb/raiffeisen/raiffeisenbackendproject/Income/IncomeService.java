package com.ubb.raiffeisen.raiffeisenbackendproject.Income;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IncomeService {
    @Autowired
    private IncomeRepository incomeRepository;

    public List<Income> getAllIncomes() {
        return incomeRepository.findAll();
    }

    public Income createIncome(Income income) {
        return incomeRepository.save(income);
    }

    // need to add methods for updating, deleting, etc.
}
