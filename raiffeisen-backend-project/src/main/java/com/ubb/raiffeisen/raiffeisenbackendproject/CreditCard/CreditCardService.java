package com.ubb.raiffeisen.raiffeisenbackendproject.CreditCard;

import com.ubb.raiffeisen.raiffeisenbackendproject.User.User;
import com.ubb.raiffeisen.raiffeisenbackendproject.User.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;

@Service
public class CreditCardService {

    private List<CreditCard> creditCardList = new ArrayList<>();

    public UserService userService = new UserService();

    User user1 = userService.findById(1L);
    User user2 = userService.findById(2L);
    User user3 = userService.findById(3L);
    User user4 = userService.findById(4L);
    User user5 = userService.findById(5L);
    private static Long counter = 6L;

    {
        creditCardList.add(new CreditCard(counter++, generateRandomCardNumber(), LocalDate.of(2025, 12, 31), "123", 1000L, 0, user1));
        creditCardList.add(new CreditCard(counter++, generateRandomCardNumber(), LocalDate.of(2026, 10, 15), "456", 2000L, 100, user2));
        creditCardList.add(new CreditCard(counter++, generateRandomCardNumber(), LocalDate.of(2024, 8, 28), "789", 1500L, 50, user3));
        creditCardList.add(new CreditCard(counter++, generateRandomCardNumber(), LocalDate.of(2023, 5, 20), "321", 3000L, 200, user4));
        creditCardList.add(new CreditCard(counter++, generateRandomCardNumber(), LocalDate.of(2025, 3, 10), "654", 2500L, 150, user5));
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

    public CreditCard findById(Long id){
        Predicate<? super CreditCard> predicate = creditId -> creditId.getId().equals(id);
        return creditCardList.stream().filter(predicate).findFirst().orElse(null);
    }

    public void saveCreditCard(CreditCard creditCard){
        creditCard.setId(counter++);
        creditCardList.add(creditCard);
    }

    public void deleteById(Long id){
        Predicate<? super CreditCard> predicate = creditId -> creditId.getId().equals(id);
        creditCardList.removeIf(predicate);
    }

}
