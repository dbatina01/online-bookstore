package com.example.onlinebookstore.web;

import com.example.onlinebookstore.api.BooksApi;
import com.example.onlinebookstore.api.model.Book;
import com.example.onlinebookstore.api.model.LoyaltyPointsBalance;
import com.example.onlinebookstore.api.model.Order;
import com.example.onlinebookstore.service.InventoryService;
import com.example.onlinebookstore.service.LoyaltyPointsService;
import com.example.onlinebookstore.service.OrderService;
import com.example.onlinebookstore.validator.LoyaltyPointsValidator;
import com.example.onlinebookstore.validator.OrderValidator;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class BookController implements BooksApi {

  private final InventoryService inventoryService;
  private final OrderService orderService;
  private final LoyaltyPointsService loyaltyPointsService;
  private final OrderValidator orderValidator;
  private final LoyaltyPointsValidator loyaltyPointsValidator;
  private final ModelMapper modelMapper;

  public ResponseEntity<List<Book>> retrieveAssortmentOfBooks() {
    return ResponseEntity.ok(inventoryService.retrieveAvailableBooks().stream().map(book -> modelMapper.map(book, Book.class)).toList());
  }

  @Override
  public ResponseEntity<Order> placeAnOrder(List<Long> bookIds) {
    orderValidator.validateRequestedBooks(bookIds);
    final var userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    return ResponseEntity.ok(modelMapper.map(orderService.submitOrder(bookIds, userDetails.getUsername()), Order.class));
  }

  @Override
  public ResponseEntity<LoyaltyPointsBalance> retrieveLoyaltyPoints() {
    final var userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    return ResponseEntity.ok(
        modelMapper.map(loyaltyPointsService.retrievePointsForCurrentUser(userDetails.getUsername()), LoyaltyPointsBalance.class));
  }

  @Override
  public ResponseEntity<LoyaltyPointsBalance> useLoyaltyPoints(Long bookId) {
    final var userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    loyaltyPointsValidator.validateLoyaltyPointsUseOnRequestedBook(bookId, userDetails.getUsername());
    return ResponseEntity.ok(modelMapper.map(loyaltyPointsService.decreasePoints(userDetails.getUsername()), LoyaltyPointsBalance.class));
  }

}
