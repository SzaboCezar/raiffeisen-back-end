package com.ubb.raiffeisen.raiffeisenbackendproject.Budget;
import com.ubb.raiffeisen.raiffeisenbackendproject.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Long> {
    List<Budget> findByUser(User user);
    //List<Budget> findByStartDateBetweenAndEndDateBetween(LocalDate start1, LocalDate end1, LocalDate start2, LocalDate end2);
    List<Budget> findByStartDateAfterAndEndDateBefore(LocalDate start, LocalDate end);

    List<Budget> findByUserAndStartDateGreaterThanEqualAndEndDateLessThanEqual(User user, LocalDate startDate, LocalDate endDate);
}

