package com.example.onlinebookstore.service.impl.discount;

import com.example.onlinebookstore.entity.BookType;
import com.example.onlinebookstore.service.DiscountStrategy;

public class BookFinalPriceCalculator {

  private DiscountStrategy discountStrategy;

  public BookFinalPriceCalculator(BookType bookType) {
    discountStrategy = switch (bookType) {
      case NEW_RELEASES -> new NewReleasesDiscount();
      case REGULAR -> new RegularDiscount();
      case OLD_EDITIONS -> new OldEditionsDiscount();
    };
  }

  public Double calculateFinalPrice(Double basePrice, Integer orderSize) {
    return discountStrategy.applyDiscount(basePrice, orderSize);
  }

}
