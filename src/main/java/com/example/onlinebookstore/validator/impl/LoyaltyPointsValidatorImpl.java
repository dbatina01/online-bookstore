package com.example.onlinebookstore.validator.impl;

import com.example.onlinebookstore.entity.BookType;
import com.example.onlinebookstore.exception.LoyaltyPointsException;
import com.example.onlinebookstore.repository.BookRepository;
import com.example.onlinebookstore.repository.CustomerRepository;
import com.example.onlinebookstore.validator.LoyaltyPointsValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.webjars.NotFoundException;

@Component
@RequiredArgsConstructor
public class LoyaltyPointsValidatorImpl implements LoyaltyPointsValidator {

  private final BookRepository bookRepository;
  private final CustomerRepository customerRepository;

  @Override
  public void validateLoyaltyPointsUseOnRequestedBook(Long bookId, String username) {
    final var book = bookRepository.findById(bookId).orElseThrow(() -> new LoyaltyPointsException("Requested book does not exist!"));

    final var bookIsNotAvailable = !book.getAvailable();
    if (bookIsNotAvailable) {
      throw new LoyaltyPointsException("You cannot request unavailable book!");
    }

    final var loyaltyPointsCannotBeUsedWithBook = book.getBookType().name().equals(BookType.NEW_RELEASES.name());
    if (loyaltyPointsCannotBeUsedWithBook) {
      throw new LoyaltyPointsException("Requested book is not eligible for use with loyalty points!");
    }

    final var customer = customerRepository.findById(username).orElseThrow(() -> new NotFoundException("User not found!"));
    final var customerDoesNotHaveEnoughLoyaltyPoints = customer.getLoyaltyPoints() < 10;
    if (customerDoesNotHaveEnoughLoyaltyPoints) {
      throw new LoyaltyPointsException("You does not have enough loyalty points!");
    }
  }

}
