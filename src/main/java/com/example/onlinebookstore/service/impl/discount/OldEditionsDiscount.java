package com.example.onlinebookstore.service.impl.discount;

import com.example.onlinebookstore.service.DiscountStrategy;

public class OldEditionsDiscount implements DiscountStrategy {

  @Override
  public Double applyDiscount(Double basePrice, Integer orderSize) {
    return orderSize < 3 ? basePrice * 0.8 : basePrice * 0.8 * 0.95;
  }

}
