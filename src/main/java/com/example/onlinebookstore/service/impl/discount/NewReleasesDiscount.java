package com.example.onlinebookstore.service.impl.discount;

import com.example.onlinebookstore.service.DiscountStrategy;

public class NewReleasesDiscount implements DiscountStrategy {

  @Override
  public Double applyDiscount(Double basePrice, Integer orderSize) {
    return basePrice * 1;
  }

}
