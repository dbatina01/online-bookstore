package com.example.onlinebookstore.service;

public interface LoyaltyPointsService {

  void increasePoints(final Integer numberOfPoints, final String username);

  LoyaltyPointsBalance decreasePoints(final String username);

  LoyaltyPointsBalance retrievePointsForCurrentUser(final String username);

}