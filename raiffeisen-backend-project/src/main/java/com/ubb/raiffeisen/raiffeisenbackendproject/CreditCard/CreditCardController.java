package com.ubb.raiffeisen.raiffeisenbackendproject.CreditCard;
import com.ubb.raiffeisen.raiffeisenbackendproject.User.User;
import com.ubb.raiffeisen.raiffeisenbackendproject.User.UserJpaRepository;
import com.ubb.raiffeisen.raiffeisenbackendproject.User.UserNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CreditCardController {

    private CreditCardJpaRepository cardJpaRepository;
    private UserJpaRepository userJpaRepository;

    public CreditCardController(CreditCardJpaRepository cardJpaRepository, UserJpaRepository userJpaRepository) {
        this.cardJpaRepository = cardJpaRepository;
        this.userJpaRepository = userJpaRepository;
    }


    @GetMapping(path = "/credit-cards")
    public List<CreditCard> getCreditCardList(){
           return cardJpaRepository.findAll();
    }

    @GetMapping(path = "/credit-card/{cardId}")
    public Optional<CreditCard> findById(@PathVariable Long cardId){
        Optional<CreditCard> creditCard = cardJpaRepository.findById(cardId);
        if(creditCard.isEmpty()) throw new CardNotFoundException("Credit Card with id: " + cardId + " does not exist!");
        return creditCard;
    }


    @PostMapping(path = "/user/{user_id}/credit-card")
    private CreditCard createCreditCardForUser(@PathVariable Long user_id, @RequestBody CreditCard creditCard){
        Optional<User> findUser = userJpaRepository.findById(user_id);
        if (findUser.isEmpty()) throw new UserNotFoundException("User with id: " + user_id + " does not exist!");

        creditCard.setUser(findUser.get());
        User checkUser = findUser.get();
        checkUser.setCreditCard(creditCard);

        return cardJpaRepository.save(creditCard);
    }

    @PostMapping(path = "/credit-card/delete{id}")
    public void deleteById(@PathVariable Long id){
        cardJpaRepository.deleteById(id);
    }


}
