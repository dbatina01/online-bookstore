package com.example.onlinebookstore.web;

import com.example.onlinebookstore.api.BooksApi;
import com.example.onlinebookstore.api.model.Book;
import com.example.onlinebookstore.service.InventoryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class BookController implements BooksApi {

  private final InventoryService inventoryService;
  private final ModelMapper modelMapper;

  public ResponseEntity<List<Book>> retrieveAssortmentOfBooks() {
    return ResponseEntity.ok(inventoryService.retrieveAvailableBooks().stream().map(book -> modelMapper.map(book, Book.class)).toList());
  }

}
