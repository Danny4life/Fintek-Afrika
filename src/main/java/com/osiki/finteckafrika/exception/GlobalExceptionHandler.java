package com.osiki.finteckafrika.exception;

import com.osiki.finteckafrika.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Optional;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(EmailNotValidException.class)
    @ResponseStatus(HttpStatus.ALREADY_REPORTED)
    public ResponseEntity<ErrorResponse> handleEmailNotValidException(final EmailNotValidException exception){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(exception.getMessage());
        errorResponse.setDebugMessage("Email not valid");

        return ResponseEntity.of(Optional.of(errorResponse));

    }

    @ExceptionHandler(EmailAlreadyTakenException.class)
    @ResponseStatus(HttpStatus.ALREADY_REPORTED)
    public ResponseEntity<ErrorResponse> handleEmailAlreadyTakenException(final EmailAlreadyTakenException exception){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(exception.getMessage());
        errorResponse.setDebugMessage("Email already taken");

        return ResponseEntity.of(Optional.of(errorResponse));

    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.ALREADY_REPORTED)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(final UserNotFoundException exception){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(exception.getMessage());
        errorResponse.setDebugMessage("User not found");

        return ResponseEntity.of(Optional.of(errorResponse));

    }

    @ExceptionHandler(TokenNotFoundException.class)
    @ResponseStatus(HttpStatus.ALREADY_REPORTED)
    public ResponseEntity<ErrorResponse> handleTokenNotFoundException(final TokenNotFoundException exception){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(exception.getMessage());
        errorResponse.setDebugMessage("Token not found");

        return ResponseEntity.of(Optional.of(errorResponse));

    }

    @ExceptionHandler(ConfirmationTokenException.class)
    @ResponseStatus(HttpStatus.ALREADY_REPORTED)
    public ResponseEntity<ErrorResponse> handleConfirmationTokenException(final ConfirmationTokenException exception){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(exception.getMessage());
        errorResponse.setDebugMessage("Email Already Confirmed");

        return ResponseEntity.of(Optional.of(errorResponse));

    }
}
