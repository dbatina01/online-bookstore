package com.example.onlinebookstore.service;

import com.example.onlinebookstore.entity.Book;
import java.util.List;

public interface InventoryService {

  List<Book> retrieveAvailableBooks();

}
