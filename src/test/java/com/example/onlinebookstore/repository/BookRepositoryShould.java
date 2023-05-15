package com.example.onlinebookstore.repository;

import com.example.onlinebookstore.entity.Book;
import com.example.onlinebookstore.entity.BookType;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@SpringBootTest
@Testcontainers
@ActiveProfiles("test")
public class BookRepositoryShould {

  @Container
  static PostgreSQLContainer postgreSqlContainer = new PostgreSQLContainer(DockerImageName.parse("postgres:latest"));

  @DynamicPropertySource
  static void setProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url", postgreSqlContainer::getJdbcUrl);
    registry.add("spring.datasource.username", postgreSqlContainer::getUsername);
    registry.add("spring.datasource.password", postgreSqlContainer::getPassword);
  }

  @Autowired
  private BookRepository bookRepository;

  @Test
  public void retrieve_available_books() {
    // given
    final var book1 = Book.builder()
        .id(1L)
        .author("Joshua Block")
        .title("Effective Java")
        .bookType(BookType.REGULAR)
        .basePrice(30.00)
        .available(Boolean.TRUE).build();
    final var book2 = Book.builder()
        .id(2L)
        .author("Iuliana Cosmina")
        .title("Pro Spring 5")
        .bookType(BookType.NEW_RELEASES)
        .basePrice(40.00)
        .available(Boolean.FALSE).build();
    bookRepository.saveAll(List.of(book1, book2));
    final var expectedResponseSize = 1;
    final var expectedBookId = 1;

    // when
    final var response = bookRepository.findAllByAvailableIsTrue();

    // then
    Assertions.assertEquals(expectedResponseSize, response.size());
    Assertions.assertEquals(expectedBookId, response.get(0).getId());
  }

}
