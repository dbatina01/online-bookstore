package com.example.onlinebookstore.advice;

import com.example.onlinebookstore.exception.LoyaltyPointsException;
import com.example.onlinebookstore.exception.OrderException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalAdviceController {

  @ExceptionHandler({OrderException.class, LoyaltyPointsException.class})
  public ResponseEntity<String> handleBadRequest(final RuntimeException e) {
    return ResponseEntity.badRequest().body(e.getMessage());
  }

}
