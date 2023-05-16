package com.example.onlinebookstore.repository;

import com.example.onlinebookstore.entity.Customer;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface CustomerRepository extends JpaRepository<Customer, String> {

  @Modifying
  @Query(value = "UPDATE users SET loyalty_points = loyalty_points + :loyaltyPoints WHERE username = :username", nativeQuery = true)
  void increaseCustomerLoyaltyPoints(Integer loyaltyPoints, String username);

  @Modifying
  @Query(value = "UPDATE users SET loyalty_points = loyalty_points - :loyaltyPoints WHERE username = :username", nativeQuery = true)
  void decreaseCustomerLoyaltyPoints(Integer loyaltyPoints, String username);

}
