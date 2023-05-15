package com.example.onlinebookstore.service;

import static org.mockito.Mockito.when;

import com.example.onlinebookstore.entity.Book;
import com.example.onlinebookstore.entity.BookType;
import com.example.onlinebookstore.repository.BookRepository;
import com.example.onlinebookstore.service.impl.OrderServiceImpl;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class OrderServiceShould {

  private OrderService orderService;

  @Mock
  private BookRepository bookRepository;

  @BeforeEach
  public void setUp() {
    this.orderService = new OrderServiceImpl(bookRepository);
  }

  @Test
  public void submit_order() {
    // given
    final var bookIds = List.of(1L, 2L, 3L);
    final var books = List.of(
        Book.builder()
            .id(1L)
            .author("Joshua Block")
            .title("Effective Java")
            .bookType(BookType.NEW_RELEASES)
            .basePrice(42.29)
            .available(Boolean.TRUE).build(),
        Book.builder()
            .id(2L)
            .author("Iuliana Cosmina")
            .title("Pro Spring 5")
            .bookType(BookType.OLD_EDITIONS)
            .basePrice(30.00)
            .available(Boolean.TRUE).build(),
        Book.builder()
            .id(3L)
            .author("Herbert Schildt")
            .title("Java: A Beginner´s Guide")
            .bookType(BookType.REGULAR)
            .basePrice(35.68)
            .available(Boolean.TRUE).build());
    when(bookRepository.findByIds(bookIds)).thenReturn(books);
    final var expectedTotalBooks = books.size();
    final var expectedTotalPrice = books.get(0).getBasePrice() + (books.get(1).getBasePrice() * 0.8 * 0.95) + (books.get(2).getBasePrice() * 0.9);

    // when
    final var response = orderService.submitOrder(bookIds);

    // then
    Assertions.assertEquals(expectedTotalBooks, response.getTotalBooks());
    Assertions.assertEquals(expectedTotalPrice, response.getTotalPrice());
  }

}
