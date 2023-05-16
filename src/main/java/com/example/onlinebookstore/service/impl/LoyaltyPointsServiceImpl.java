package com.example.onlinebookstore.service.impl;

import com.example.onlinebookstore.repository.CustomerRepository;
import com.example.onlinebookstore.service.LoyaltyPointsBalance;
import com.example.onlinebookstore.service.LoyaltyPointsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

@Service
@RequiredArgsConstructor
public class LoyaltyPointsServiceImpl implements LoyaltyPointsService {

  private final CustomerRepository customerRepository;

  @Override
  public void increasePoints(Integer numberOfPoints, String username) {
    customerRepository.increaseCustomerLoyaltyPoints(numberOfPoints, username);
  }

  @Override
  public LoyaltyPointsBalance decreasePoints(String username) {
    final var customer = customerRepository.findById(username).orElseThrow(() -> new NotFoundException("User not found!"));

    customerRepository.decreaseCustomerLoyaltyPoints(10, username);

    return new LoyaltyPointsBalance(customer.getUsername(), customer.getLoyaltyPoints() - 10);
  }

  @Override
  public LoyaltyPointsBalance retrievePointsForCurrentUser(String username) {
    final var customer = customerRepository.findById(username).orElseThrow(() -> new NotFoundException("User not found!"));

    return new LoyaltyPointsBalance(customer.getUsername(), customer.getLoyaltyPoints());
  }

}
