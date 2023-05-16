package com.example.onlinebookstore.service.impl;

import com.example.onlinebookstore.entity.Book;
import com.example.onlinebookstore.repository.BookRepository;
import com.example.onlinebookstore.service.LoyaltyPointsService;
import com.example.onlinebookstore.service.Order;
import com.example.onlinebookstore.service.OrderService;
import com.example.onlinebookstore.service.impl.discount.BookFinalPriceCalculator;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

  private final BookRepository bookRepository;
  private final LoyaltyPointsService loyaltyPointsService;

  @Override
  public Order submitOrder(List<Long> bookIds, String username) {
    final var books = bookRepository.findByIds(bookIds);
    final var totalPrice = books.stream().map(book -> calculateBookFinalPrice(book, books.size())).reduce(0.0, Double::sum);

    loyaltyPointsService.increasePoints(books.size(), username);

    return new Order(books.size(), totalPrice);
  }

  private Double calculateBookFinalPrice(Book book, Integer orderSize) {
    BookFinalPriceCalculator bookFinalPriceCalculator = new BookFinalPriceCalculator(book.getBookType());
    return bookFinalPriceCalculator.calculateFinalPrice(book.getBasePrice(), orderSize);
  }

}
