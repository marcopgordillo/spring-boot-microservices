package com.example.orderservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String customerEmail;

  private String customerAddress;

  @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
  @JsonManagedReference
  private List<OrderItem> items;
}
