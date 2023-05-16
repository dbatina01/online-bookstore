package com.example.onlinebookstore.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.onlinebookstore.entity.Customer;
import com.example.onlinebookstore.repository.CustomerRepository;
import com.example.onlinebookstore.service.impl.LoyaltyPointsServiceImpl;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class LoyaltyPointsServiceShould {

  private LoyaltyPointsService loyaltyPointsService;

  @Mock
  private CustomerRepository customerRepository;

  @BeforeEach
  public void setUp() {
    this.loyaltyPointsService = new LoyaltyPointsServiceImpl(customerRepository);
  }

  @Test
  public void decrease_loyalty_points_by_amount_of_10_points() {
    // given
    final var customer = Customer.builder()
        .username("customer")
        .loyaltyPoints(12).build();
    final var expectedResponse = new LoyaltyPointsBalance(
        customer.getUsername(), customer.getLoyaltyPoints() - 10);
    when(customerRepository.findById(customer.getUsername())).thenReturn(Optional.of(customer));

    // when
    final var response = loyaltyPointsService.decreasePoints(customer.getUsername());

    // then
    Assertions.assertEquals(expectedResponse, response);
    verify(customerRepository, times(1)).decreaseCustomerLoyaltyPoints(10, customer.getUsername());
  }

}
