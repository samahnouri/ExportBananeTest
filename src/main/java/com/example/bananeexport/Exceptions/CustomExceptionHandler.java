package com.example.bananeexport.Exceptions;

import com.example.bananeexport.Controllers.CommandeController;
import com.example.bananeexport.Controllers.DestinataireController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = {CommandeController.class, DestinataireController.class})
public class CustomExceptionHandler {

    @ExceptionHandler(CommandeException.class)
    public ResponseEntity<String> handleCommandeException(CommandeException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DestinataireException.class)
    public ResponseEntity<String> handleDestinataireException(DestinataireException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        return new ResponseEntity<>("An unexpected error occurred.", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

