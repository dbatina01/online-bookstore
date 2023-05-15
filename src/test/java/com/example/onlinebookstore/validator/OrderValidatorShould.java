package com.example.onlinebookstore.validator;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.when;

import com.example.onlinebookstore.entity.Book;
import com.example.onlinebookstore.entity.BookType;
import com.example.onlinebookstore.exception.OrderException;
import com.example.onlinebookstore.repository.BookRepository;
import com.example.onlinebookstore.validator.impl.OrderValidatorImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class OrderValidatorShould {

  private OrderValidator orderValidator;

  @Mock
  private BookRepository bookRepository;

  @BeforeEach
  public void setUp() {
    this.orderValidator = new OrderValidatorImpl(bookRepository);
  }

  @Test
  public void throw_exception_if_order_is_empty() {
    // given
    final var bookIds = new ArrayList<Long>();

    // when & then
    assertThatThrownBy(() -> orderValidator.validateRequestedBooks(bookIds))
        .isInstanceOf(OrderException.class)
        .hasMessage("You have to chose at least one book for an order!");
  }

  @Test
  public void throw_exception_if_order_contains_nonexistent_book() {
    // given
    final var bookIds = List.of(1L);
    when(bookRepository.findById(bookIds.get(0))).thenReturn(Optional.empty());

    // when & then
    assertThatThrownBy(() -> orderValidator.validateRequestedBooks(bookIds))
        .isInstanceOf(OrderException.class)
        .hasMessage("You cannot order nonexistent book!");
  }

  @Test
  public void throw_exception_if_order_contains_unavailable_book() {
    // given
    final var bookIds = List.of(1L);
    final var book = Book.builder()
        .id(1L)
        .author("Joshua Block")
        .title("Effective Java")
        .bookType(BookType.NEW_RELEASES)
        .basePrice(30.00)
        .available(Boolean.FALSE).build();
    when(bookRepository.findById(bookIds.get(0))).thenReturn(Optional.of(book));

    // when & then
    assertThatThrownBy(() -> orderValidator.validateRequestedBooks(bookIds))
        .isInstanceOf(OrderException.class)
        .hasMessage("You cannot order unavailable book!");
  }
}
