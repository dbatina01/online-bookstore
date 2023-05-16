package com.example.onlinebookstore.service;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class LoyaltyPointsBalance {

  private final String username;
  private final Integer loyaltyPoints;

}
