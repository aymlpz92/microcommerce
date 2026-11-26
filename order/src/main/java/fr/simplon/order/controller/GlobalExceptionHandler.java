package fr.simplon.order.controller;

import java.time.LocalDateTime;

import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import fr.simplon.order.entity.ExceptionData;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ExceptionData> handleBadRequestException(BadRequestException exception) {
        ExceptionData exceptionData = new ExceptionData();
        exceptionData.setHttpStatusCode(HttpStatus.BAD_REQUEST.value());
        exceptionData.setMessage(exception.getMessage());
        exceptionData.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(exceptionData, HttpStatus.valueOf(exceptionData.getHttpStatusCode()));
    }
}
