package com.ubb.raiffeisen.raiffeisenbackendproject.CreditCard;
import com.ubb.raiffeisen.raiffeisenbackendproject.User.User;
import com.ubb.raiffeisen.raiffeisenbackendproject.User.UserJpaRepository;
import com.ubb.raiffeisen.raiffeisenbackendproject.User.UserNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controller for managing credit card-related operations.
 */
@RestController
public class CreditCardController {
    private CreditCardJpaRepository cardJpaRepository;
    private UserJpaRepository userJpaRepository;

    /**
     * Constructs a new CreditCardController with the specified repositories.
     *
     * @param cardJpaRepository The repository for credit card entities.
     * @param userJpaRepository The repository for user entities.
     */
    public CreditCardController(CreditCardJpaRepository cardJpaRepository, UserJpaRepository userJpaRepository) {
        this.cardJpaRepository = cardJpaRepository;
        this.userJpaRepository = userJpaRepository;
    }

    /**
     * Retrieves a list of all credit cards.
     *
     * @return A list of all credit cards.
     */
    @GetMapping(path = "/credit-cards")
    public List<CreditCard> getCreditCardList(){
           return cardJpaRepository.findAll();
    }

    /**
     * Retrieves the credit card with the specified ID.
     *
     * @param cardId The ID of the credit card to retrieve.
     * @return The credit card with the specified ID, if found.
     * @throws CardNotFoundException If no credit card with the specified ID exists.
     */
    @GetMapping(path = "/credit-card/{cardId}")
    public Optional<CreditCard> findById(@PathVariable Long cardId){
        Optional<CreditCard> creditCard = cardJpaRepository.findById(cardId);
        if(creditCard.isEmpty()) throw new CardNotFoundException("Credit Card with id: " + cardId + " does not exist!");
        return creditCard;
    }

    /**
     * Creates a new credit card for the specified user.
     *
     * @param user_id The ID of the user for whom to create the credit card.
     * @param creditCard The credit card to create.
     * @return The created credit card.
     * @throws UserNotFoundException If no user with the specified ID exists.
     */
    @PostMapping(path = "/user/{user_id}/credit-card")
    private CreditCard createCreditCardForUser(@PathVariable Long user_id, @RequestBody CreditCard creditCard){
        Optional<User> findUser = userJpaRepository.findById(user_id);
        if (findUser.isEmpty()) throw new UserNotFoundException("User with id: " + user_id + " does not exist!");

        creditCard.setUser(findUser.get());
        User checkUser = findUser.get();
        checkUser.setCreditCard(creditCard);

        return cardJpaRepository.save(creditCard);
    }


    /**
     * Deletes the credit card with the specified ID.
     *
     * @param id The ID of the credit card to delete.
     */
    @PostMapping(path = "/credit-card/delete{id}")
    public void deleteById(@PathVariable Long id){
        cardJpaRepository.deleteById(id);
    }


}
