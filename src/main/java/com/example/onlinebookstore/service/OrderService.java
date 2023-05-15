package com.example.onlinebookstore.service;

import java.util.List;

public interface OrderService {

  Order submitOrder(List<Long> bookIds);

}
