package com.example.onlinebookstore.service.impl;

import com.example.onlinebookstore.entity.Book;
import com.example.onlinebookstore.repository.BookRepository;
import com.example.onlinebookstore.service.InventoryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

  private final BookRepository bookRepository;

  @Override
  public List<Book> retrieveAvailableBooks() {
    return bookRepository.findAllByAvailableIsTrue();
  }

}
