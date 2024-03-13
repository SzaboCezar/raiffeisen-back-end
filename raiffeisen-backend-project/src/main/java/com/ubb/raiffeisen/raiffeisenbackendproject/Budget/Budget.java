package com.ubb.raiffeisen.raiffeisenbackendproject.Budget;

import com.ubb.raiffeisen.raiffeisenbackendproject.User.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Budget {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double totalAmount;
    private LocalDate startDate;
    private LocalDate endDate;

    @ManyToOne
    private User user;
}
