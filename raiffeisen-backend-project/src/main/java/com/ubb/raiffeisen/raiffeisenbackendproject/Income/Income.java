package com.ubb.raiffeisen.raiffeisenbackendproject.Income;

import com.ubb.raiffeisen.raiffeisenbackendproject.User.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Income {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amount;
    private String description;
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private Categories incomeCategories;

    @ManyToOne
    private User user;

}
