package com.example.onlinebookstore.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity
@Table(name = "book")
public class Book {

  @Id
  @Column(name = "id")
  @SequenceGenerator(name = "book_id_seq", sequenceName = "book_id_seq", allocationSize = 1)
  @GeneratedValue(generator = "book_id_seq", strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "author")
  private String author;

  @Column(name = "title")
  private String title;

  @Column(name = "book_type")
  @Enumerated(EnumType.STRING)
  private BookType bookType;

  @Column(name = "base_price")
  private Double basePrice;

  @Column(name = "available")
  private Boolean available;

}
