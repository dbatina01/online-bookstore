package com.example.onlinebookstore.validator;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.when;

import com.example.onlinebookstore.entity.Book;
import com.example.onlinebookstore.entity.BookType;
import com.example.onlinebookstore.entity.Customer;
import com.example.onlinebookstore.exception.LoyaltyPointsException;
import com.example.onlinebookstore.repository.BookRepository;
import com.example.onlinebookstore.repository.CustomerRepository;
import com.example.onlinebookstore.validator.impl.LoyaltyPointsValidatorImpl;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class LoyaltyPointsValidatorShould {

  private LoyaltyPointsValidator loyaltyPointsValidator;

  @Mock
  private BookRepository bookRepository;

  @Mock
  private CustomerRepository customerRepository;

  @BeforeEach
  public void setUp() {
    this.loyaltyPointsValidator = new LoyaltyPointsValidatorImpl(bookRepository, customerRepository);
  }

  @Test
  public void throw_exception_if_requested_book_does_not_exist() {
    // given
    final var bookId = 1L;
    final var customer = "customer";
    when(bookRepository.findById(bookId)).thenReturn(Optional.empty());

    // when & then
    assertThatThrownBy(() -> loyaltyPointsValidator.validateLoyaltyPointsUseOnRequestedBook(bookId, customer))
        .isInstanceOf(LoyaltyPointsException.class)
        .hasMessage("Requested book does not exist!");
  }

  @Test
  public void throw_exception_if_requested_book_is_not_available() {
    // given
    final var bookId = 1L;
    final var customer = "customer";
    final var book = Book.builder()
        .id(1L)
        .author("Joshua Block")
        .title("Effective Java")
        .bookType(BookType.NEW_RELEASES)
        .basePrice(30.00)
        .available(Boolean.FALSE).build();
    when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));

    // when & then
    assertThatThrownBy(() -> loyaltyPointsValidator.validateLoyaltyPointsUseOnRequestedBook(bookId, customer))
        .isInstanceOf(LoyaltyPointsException.class)
        .hasMessage("You cannot request unavailable book!");
  }

  @Test
  public void throw_exception_if_requested_book_is_new_releases_type() {
    // given
    final var bookId = 1L;
    final var customer = "customer";
    final var book = Book.builder()
        .id(1L)
        .author("Joshua Block")
        .title("Effective Java")
        .bookType(BookType.NEW_RELEASES)
        .basePrice(30.00)
        .available(Boolean.TRUE).build();
    when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));

    // when & then
    assertThatThrownBy(() -> loyaltyPointsValidator.validateLoyaltyPointsUseOnRequestedBook(bookId, customer))
        .isInstanceOf(LoyaltyPointsException.class)
        .hasMessage("Requested book is not eligible for use with loyalty points!");
  }

  @Test
  public void throw_exception_if_customer_does_not_have_enough_points() {
    // given
    final var bookId = 1L;
    final var customer = Customer.builder()
        .username("customer")
        .loyaltyPoints(7).build();
    final var book = Book.builder()
        .id(1L)
        .author("Joshua Block")
        .title("Effective Java")
        .bookType(BookType.OLD_EDITIONS)
        .basePrice(30.00)
        .available(Boolean.TRUE).build();
    when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));
    when(customerRepository.findById(customer.getUsername())).thenReturn(Optional.of(customer));

    // when & then
    assertThatThrownBy(() -> loyaltyPointsValidator.validateLoyaltyPointsUseOnRequestedBook(bookId, customer.getUsername()))
        .isInstanceOf(LoyaltyPointsException.class)
        .hasMessage("You does not have enough loyalty points!");
  }

}
