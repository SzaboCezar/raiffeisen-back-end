package com.ubb.raiffeisen.raiffeisenbackendproject.HandleException;

import com.ubb.raiffeisen.raiffeisenbackendproject.CreditCard.CardNotFoundException;
import com.ubb.raiffeisen.raiffeisenbackendproject.Transaction.InsufficientFundsException;
import com.ubb.raiffeisen.raiffeisenbackendproject.Transaction.TransactionNotFoundException;
import com.ubb.raiffeisen.raiffeisenbackendproject.User.UserNotFoundException;
import jakarta.annotation.Priority;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleUserNotFoundException(UserNotFoundException ex) {
        ErrorDetails errorDetails = new ErrorDetails(ex.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(CardNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleCreditCardNotFoundException(CardNotFoundException ex){
        ErrorDetails errorDetails = new ErrorDetails(ex.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TransactionNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleTransactionNotFoundException(TransactionNotFoundException ex){
        ErrorDetails errorDetails = new ErrorDetails(ex.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InsufficientFundsException.class)
    public ResponseEntity<ErrorDetails> handleInsufficientFunds(InsufficientFundsException ex){
        ErrorDetails errorDetails = new ErrorDetails(ex.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }



}
