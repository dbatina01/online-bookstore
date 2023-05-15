package com.example.onlinebookstore.service.impl.discount;

import com.example.onlinebookstore.service.DiscountStrategy;

public class RegularDiscount implements DiscountStrategy {

  @Override
  public Double applyDiscount(Double basePrice, Integer orderSize) {
    return orderSize < 3 ? basePrice * 1 : basePrice * 0.9;
  }

}
