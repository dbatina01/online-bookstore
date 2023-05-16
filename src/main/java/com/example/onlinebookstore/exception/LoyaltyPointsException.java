package com.example.onlinebookstore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class LoyaltyPointsException extends RuntimeException {

  public LoyaltyPointsException(final String message) {
    super(message);
  }

}
