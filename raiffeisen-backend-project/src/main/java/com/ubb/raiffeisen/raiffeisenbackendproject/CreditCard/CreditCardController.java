package com.ubb.raiffeisen.raiffeisenbackendproject.CreditCard;
import org.springframework.web.bind.annotation.GetMapping;
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
}
