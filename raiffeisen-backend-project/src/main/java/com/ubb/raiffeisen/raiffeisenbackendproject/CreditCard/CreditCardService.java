package com.ubb.raiffeisen.raiffeisenbackendproject.CreditCard;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class CreditCardService {

    private List<CreditCard> creditCardList = new ArrayList<>();
    private static Long counter = 1L;

    {
        creditCardList.add(new CreditCard(counter++, generateRandomCardNumber(), LocalDate.of(2025, 12, 31), "123", 1000L, 0));
        creditCardList.add(new CreditCard(counter++, generateRandomCardNumber(), LocalDate.of(2026, 10, 15), "456", 2000L, 100));
        creditCardList.add(new CreditCard(counter++, generateRandomCardNumber(), LocalDate.of(2024, 8, 28), "789", 1500L, 50));
        creditCardList.add(new CreditCard(counter++, generateRandomCardNumber(), LocalDate.of(2023, 5, 20), "321", 3000L, 200));
        creditCardList.add(new CreditCard(counter++, generateRandomCardNumber(), LocalDate.of(2025, 3, 10), "654", 2500L, 150));
    }

    private static String generateRandomCardNumber() {
        Random random = new Random();
        StringBuilder cardNumberBuilder = new StringBuilder();
        for (int i = 0; i < 16; i++) {
            int digit = random.nextInt(10);
            cardNumberBuilder.append(digit);
        }
        return cardNumberBuilder.toString();
    }

    public List<CreditCard> getCreditCardList(){
        return creditCardList;
    }

}
