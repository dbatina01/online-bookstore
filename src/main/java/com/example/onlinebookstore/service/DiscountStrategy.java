package com.example.onlinebookstore.service;

public interface DiscountStrategy {

  Double applyDiscount(Double basePrice, Integer orderSize);

}
