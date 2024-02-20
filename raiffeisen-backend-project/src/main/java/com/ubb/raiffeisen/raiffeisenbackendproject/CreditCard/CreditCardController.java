package com.ubb.raiffeisen.raiffeisenbackendproject.CreditCard;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class CreditCardController {

    private CreditCardService cardService;

    public CreditCardController(CreditCardService cardService) {
        this.cardService = cardService;
    }


    @GetMapping(path = "/credit-cards")
    public List<CreditCard> getCreditCardList(){
           return cardService.getCreditCardList();
    }

    @GetMapping(path = "/credit-card/{cardId}")
    public CreditCard findById(@PathVariable Long cardId){
        CreditCard creditCard = cardService.findById(cardId);
        if(creditCard == null) throw new CreditNotFoundException("Credit Card with id: " + cardId + " does not exist!");
        return creditCard;
    }

    
}
