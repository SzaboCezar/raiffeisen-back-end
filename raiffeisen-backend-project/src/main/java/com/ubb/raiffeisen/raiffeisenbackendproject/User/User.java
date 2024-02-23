package com.ubb.raiffeisen.raiffeisenbackendproject.User;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ubb.raiffeisen.raiffeisenbackendproject.CreditCard.CreditCard;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "user_details")
public class User {
    @Id
    @GeneratedValue
    private Long userID;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private LocalDate dateOfBirth;
    private String address;
    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private CreditCard creditCard;
}
