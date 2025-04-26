package com.urlshortener.exceptionhandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.sql.SQLException;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleNullPointerException(NullPointerException ex) {
        log.error("NullPointerException occurred: {}", ex.getMessage(), ex);
        return new ResponseEntity<>("Null value encountered!", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ClassNotFoundException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handleClassNotFoundException(ClassNotFoundException ex) {
        log.error("ClassNotFoundException occurred: {}", ex.getMessage(), ex);
        return new ResponseEntity<>("An unexpected error occurred!", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(SQLException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handleSQLException(SQLException ex) {
        log.error("SQLException occurred: {}", ex.getMessage(), ex);
        return new ResponseEntity<>("An unexpected error occurred!", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        log.error("Unexpected error: {}", ex.getMessage(), ex);
        return new ResponseEntity<>("An unexpected error occurred!", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(JsonProcessingException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handleJsonProcessingException(JsonProcessingException ex) {
        log.error("Unexpected error: {}", ex.getMessage(), ex);
        return new ResponseEntity<>("An unexpected error occurred!", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
