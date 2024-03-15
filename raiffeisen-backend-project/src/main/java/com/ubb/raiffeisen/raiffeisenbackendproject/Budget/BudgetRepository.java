package com.ubb.raiffeisen.raiffeisenbackendproject.Budget;
import com.ubb.raiffeisen.raiffeisenbackendproject.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Long> {
    List<Budget> findByUser(User user);

    List<Budget> findAllByStartDateAndEndDate(LocalDate start, LocalDate end);

    List<Budget> findByUserAndStartDateGreaterThanEqualAndEndDateLessThanEqual(User user, LocalDate startDate, LocalDate endDate);
}

