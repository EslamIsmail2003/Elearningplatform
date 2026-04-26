package com.elearning.ELearningPlatform.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseException> handleResourceNotFound(ResourceNotFoundException rEx) {
        ErrorResponseException error = new ErrorResponseException(
                HttpStatus.NOT_FOUND.value(), rEx.getMessage(), LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponseException> handleRunTimeException(RuntimeException rE) {
        ErrorResponseException error = new ErrorResponseException(
                HttpStatus.INTERNAL_SERVER_ERROR.value(), rE.getMessage(), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseException> handleValidationErrors(MethodArgumentNotValidException err){
        String message = err.getBindingResult().getFieldErrors().stream().map(error -> error.getField() + ": " + error.getDefaultMessage()).collect(Collectors.joining(", "));

        ErrorResponseException error = new ErrorResponseException(
                HttpStatus.BAD_REQUEST.value(),message,LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

}
