package com.example.onlinebookstore.service;

import com.example.onlinebookstore.entity.Book;
import com.example.onlinebookstore.entity.BookType;
import com.example.onlinebookstore.service.impl.discount.BookFinalPriceCalculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BookFinalPriceCalculatorShould {

  @Test
  public void not_apply_any_discount_on_new_releases_book() {
    // given
    final var book = Book.builder()
        .id(1L)
        .author("Joshua Block")
        .title("Effective Java")
        .bookType(BookType.NEW_RELEASES)
        .basePrice(30.00)
        .available(Boolean.TRUE).build();
    final var bookFinalPriceCalculator = new BookFinalPriceCalculator(book.getBookType());
    final var orderSize = 4;
    final var expectedFinalPrice = book.getBasePrice();

    // when
    final var response = bookFinalPriceCalculator.calculateFinalPrice(book.getBasePrice(), orderSize);

    // then
    Assertions.assertEquals(expectedFinalPrice, response);
  }

  @Test
  public void not_apply_any_discount_on_regular_book_if_order_size_is_less_than_3() {
    // given
    final var book = Book.builder()
        .id(1L)
        .author("Joshua Block")
        .title("Effective Java")
        .bookType(BookType.REGULAR)
        .basePrice(30.00)
        .available(Boolean.TRUE).build();
    final var bookFinalPriceCalculator = new BookFinalPriceCalculator(book.getBookType());
    final var orderSize = 2;
    final var expectedFinalPrice = book.getBasePrice();

    // when
    final var response = bookFinalPriceCalculator.calculateFinalPrice(book.getBasePrice(), orderSize);

    // then
    Assertions.assertEquals(expectedFinalPrice, response);
  }

  @Test
  public void apply_10_percent_discount_on_regular_book_if_order_size_is_greater_than_2() {
    // given
    final var book = Book.builder()
        .id(1L)
        .author("Joshua Block")
        .title("Effective Java")
        .bookType(BookType.REGULAR)
        .basePrice(30.00)
        .available(Boolean.TRUE).build();
    final var bookFinalPriceCalculator = new BookFinalPriceCalculator(book.getBookType());
    final var orderSize = 3;
    final var expectedFinalPrice = book.getBasePrice() * 0.9;

    // when
    final var response = bookFinalPriceCalculator.calculateFinalPrice(book.getBasePrice(), orderSize);

    // then
    Assertions.assertEquals(expectedFinalPrice, response);
  }

  @Test
  public void apply_20_percent_discount_on_old_editions_book_if_order_size_is_less_than_2() {
    // given
    final var book = Book.builder()
        .id(1L)
        .author("Joshua Block")
        .title("Effective Java")
        .bookType(BookType.OLD_EDITIONS)
        .basePrice(30.00)
        .available(Boolean.TRUE).build();
    final var bookFinalPriceCalculator = new BookFinalPriceCalculator(book.getBookType());
    final var orderSize = 2;
    final var expectedFinalPrice = book.getBasePrice() * 0.8;

    // when
    final var response = bookFinalPriceCalculator.calculateFinalPrice(book.getBasePrice(), orderSize);

    // then
    Assertions.assertEquals(expectedFinalPrice, response);
  }

  @Test
  public void apply_20_percent_discount_and_additional_5_percent_on_old_editions_book_if_order_size_is_greater_than_2() {
    // given
    final var book = Book.builder()
        .id(1L)
        .author("Joshua Block")
        .title("Effective Java")
        .bookType(BookType.OLD_EDITIONS)
        .basePrice(30.00)
        .available(Boolean.TRUE).build();
    final var bookFinalPriceCalculator = new BookFinalPriceCalculator(book.getBookType());
    final var orderSize = 3;
    final var expectedFinalPrice = book.getBasePrice() * 0.8 * 0.95;

    // when
    final var response = bookFinalPriceCalculator.calculateFinalPrice(book.getBasePrice(), orderSize);

    // then
    Assertions.assertEquals(expectedFinalPrice, response);
  }

}
