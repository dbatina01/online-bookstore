package com.example.onlinebookstore.repository;

import com.example.onlinebookstore.entity.Book;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

  List<Book> findAllByAvailableIsTrue();

  @Query(value = "SELECT * FROM book b WHERE b.id IN :ids", nativeQuery = true)
  List<Book> findByIds(List<Long> ids);

}
