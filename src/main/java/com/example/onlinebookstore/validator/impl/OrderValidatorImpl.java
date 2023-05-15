package com.example.onlinebookstore.validator.impl;

import com.example.onlinebookstore.exception.OrderException;
import com.example.onlinebookstore.repository.BookRepository;
import com.example.onlinebookstore.validator.OrderValidator;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderValidatorImpl implements OrderValidator {

  private final BookRepository bookRepository;

  @Override
  public void validateRequestedBooks(List<Long> bookIds) {
    if (bookIds.isEmpty()) {
      throw new OrderException("You have to chose at least one book for an order!");
    }

    bookIds.forEach(this::checkBookExistenceAndAvailability);
  }

  private void checkBookExistenceAndAvailability(Long bookId) {
    final var optionalBook = bookRepository.findById(bookId);
    if (optionalBook.isEmpty()) {
      throw new OrderException("You cannot order nonexistent book!");
    }

    final var bookIsNotAvailable = !optionalBook.get().getAvailable();
    if (bookIsNotAvailable) {
      throw new OrderException(("You cannot order unavailable book!"));
    }
  }

}
