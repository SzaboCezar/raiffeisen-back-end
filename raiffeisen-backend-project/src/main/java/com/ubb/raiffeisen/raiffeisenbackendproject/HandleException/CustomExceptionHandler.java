package com.ubb.raiffeisen.raiffeisenbackendproject.HandleException;

import com.ubb.raiffeisen.raiffeisenbackendproject.CreditCard.CardNotFoundException;
import com.ubb.raiffeisen.raiffeisenbackendproject.User.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleUserNotFoundException(Exception ex) {
        ErrorDetails errorDetails = new ErrorDetails(ex.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(CardNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleCreditCardNotFoundException(Exception ex){
        ErrorDetails errorDetails = new ErrorDetails(ex.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }


}
