package com.ubb.raiffeisen.raiffeisenbackendproject.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface IncomeRepository extends JpaRepository<Income, Long> {
    List<Income> findByUserUserID(Long userId);
    List<Income> findByIncomeCategories(Categories category);

    List<Income> findByDateBetween(LocalDate startDate, LocalDate endDate);

    List<Income> findByUserUserIDAndDateBetween(Long userId, LocalDate startDate, LocalDate endDate);

    List<Income> findByAmount(Double amount);
    List<Income> findByDescription(String description);
    List<Income> findByAmountAndDescription(Double amount, String description);
}
