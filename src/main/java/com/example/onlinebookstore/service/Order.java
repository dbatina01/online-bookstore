package com.example.onlinebookstore.service;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Order {

  private final int totalBooks;
  private final double totalPrice;

}
