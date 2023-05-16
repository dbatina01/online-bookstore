package com.example.onlinebookstore.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "users")
public class Customer {

  @Id
  @Column(name = "username")
  private String username;

  @Column(name = "password")
  @JsonIgnore
  private String password;

  @Column(name = "enabled")
  @JsonIgnore
  private Boolean enabled;

  @Column(name = "loyalty_points")
  private Integer loyaltyPoints;

}
