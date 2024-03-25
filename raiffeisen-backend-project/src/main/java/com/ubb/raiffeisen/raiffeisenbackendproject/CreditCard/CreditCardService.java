package com.ubb.raiffeisen.raiffeisenbackendproject.CreditCard;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CreditCardService {

    private final CreditCardJpaRepository cardJpaRepository;

    public CreditCardService(CreditCardJpaRepository cardJpaRepository) {
        this.cardJpaRepository = cardJpaRepository;
    }

    public List<CreditCard> getCreditCardList(){
        return cardJpaRepository.findAll();
    }

    public Optional<CreditCard> findCreditCardById(Long id){
        return cardJpaRepository.findById(id);
    }

    public CreditCard createCreditCardForUser(CreditCard creditCard){
        cardJpaRepository.save(creditCard);
        return creditCard;
    }

    public void deleteCreditById(Long id){
        cardJpaRepository.deleteById(id);
    }
}
