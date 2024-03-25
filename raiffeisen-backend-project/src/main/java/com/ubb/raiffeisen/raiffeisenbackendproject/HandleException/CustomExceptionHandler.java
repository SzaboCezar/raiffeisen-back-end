package com.ubb.raiffeisen.raiffeisenbackendproject.HandleException;
import com.ubb.raiffeisen.raiffeisenbackendproject.Auth.AuthFailedException;
import com.ubb.raiffeisen.raiffeisenbackendproject.CreditCard.CardNotFoundException;
import com.ubb.raiffeisen.raiffeisenbackendproject.Expense.ExpenseNotFoundException;
import com.ubb.raiffeisen.raiffeisenbackendproject.Expense.InsufficientFundsException;
import com.ubb.raiffeisen.raiffeisenbackendproject.User.UserNotFoundException;
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

    @ExceptionHandler(ExpenseNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleTransactionNotFoundException(ExpenseNotFoundException ex){
        ErrorDetails errorDetails = new ErrorDetails(ex.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InsufficientFundsException.class)
    public ResponseEntity<ErrorDetails> handleInsufficientFunds(InsufficientFundsException ex){
        ErrorDetails errorDetails = new ErrorDetails(ex.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AuthFailedException.class)
    public ResponseEntity<ErrorDetails> handleAuthFailed(AuthFailedException ex){
        ErrorDetails errorDetails = new ErrorDetails(ex.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);
    }
}
