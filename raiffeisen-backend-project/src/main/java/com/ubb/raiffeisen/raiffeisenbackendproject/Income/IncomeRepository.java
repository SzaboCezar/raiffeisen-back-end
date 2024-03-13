package com.ubb.raiffeisen.raiffeisenbackendproject.Income;
import com.ubb.raiffeisen.raiffeisenbackendproject.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IncomeRepository extends JpaRepository<Income, Long> {
    //custom query methods here if needed
    Optional<User> findByUser(String username);
}
