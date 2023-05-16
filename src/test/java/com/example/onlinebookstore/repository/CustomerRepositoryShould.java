package com.example.onlinebookstore.repository;

import com.example.onlinebookstore.entity.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@SpringBootTest
@Testcontainers
@ActiveProfiles("test")
public class CustomerRepositoryShould {

  @Container
  static PostgreSQLContainer postgreSqlContainer = new PostgreSQLContainer(DockerImageName.parse("postgres:latest"));

  @DynamicPropertySource
  static void setProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url", postgreSqlContainer::getJdbcUrl);
    registry.add("spring.datasource.username", postgreSqlContainer::getUsername);
    registry.add("spring.datasource.password", postgreSqlContainer::getPassword);
  }

  @Autowired
  private CustomerRepository customerRepository;

  @Test
  public void increase_customer_loyalty_points() throws NotFoundException {
    // given
    final var customer = Customer.builder()
        .username("username")
        .password("password")
        .enabled(Boolean.TRUE)
        .loyaltyPoints(3).build();
    customerRepository.save(customer);
    final var loyaltyPointsBonus = 3;
    final var expectedLoyaltyPoints = customer.getLoyaltyPoints() + loyaltyPointsBonus;

    // when
    customerRepository.increaseCustomerLoyaltyPoints(loyaltyPointsBonus, customer.getUsername());

    // then
    final var updatedCustomer = customerRepository.findById(customer.getUsername()).orElseThrow(NotFoundException::new);
    Assertions.assertEquals(expectedLoyaltyPoints, updatedCustomer.getLoyaltyPoints());
  }

  @Test
  public void decrease_customer_loyalty_points() throws NotFoundException {
    // given
    final var customer = Customer.builder()
        .username("username")
        .password("password")
        .enabled(Boolean.TRUE)
        .loyaltyPoints(3).build();
    customerRepository.save(customer);
    final var loyaltyPointsDecreaseAmount = 3;
    final var expectedLoyaltyPoints = customer.getLoyaltyPoints() - loyaltyPointsDecreaseAmount;

    // when
    customerRepository.decreaseCustomerLoyaltyPoints(loyaltyPointsDecreaseAmount, customer.getUsername());

    // then
    final var updatedCustomer = customerRepository.findById(customer.getUsername()).orElseThrow(NotFoundException::new);
    Assertions.assertEquals(expectedLoyaltyPoints, updatedCustomer.getLoyaltyPoints());
  }

}
