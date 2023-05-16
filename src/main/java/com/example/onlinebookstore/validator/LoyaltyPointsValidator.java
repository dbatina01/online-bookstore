package com.example.onlinebookstore.validator;

public interface LoyaltyPointsValidator {

  void validateLoyaltyPointsUseOnRequestedBook(Long bookId, String username);

}
