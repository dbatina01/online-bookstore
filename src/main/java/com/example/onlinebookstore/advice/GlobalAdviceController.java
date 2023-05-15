package com.example.onlinebookstore.advice;

import com.example.onlinebookstore.exception.OrderException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalAdviceController {

  @ExceptionHandler({OrderException.class})
  public ResponseEntity<String> handleBadRequest(final OrderException e) {
    return ResponseEntity.badRequest().body(e.getMessage());
  }

}
