package com.ubb.raiffeisen.raiffeisenbackendproject.CreditCard;

import com.ubb.raiffeisen.raiffeisenbackendproject.User.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "credit_cards")
public class CreditCard {
    @Id
    @GeneratedValue
    private Long creditCardID;
    private String cardNumber;
    private LocalDate expirationDate;
    private String CVV;
    private Long amount;
    private Integer points;
    @OneToOne
    private User user;
}
