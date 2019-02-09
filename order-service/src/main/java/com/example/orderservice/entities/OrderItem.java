package com.example.orderservice.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;


@Entity
@Table(name = "order_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Long productId;

  private int quantity;

  @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
  @ManyToOne(fetch = FetchType.LAZY)
  @JsonBackReference
  private Order order;

  private BigDecimal productPrice;

  public BigDecimal getPrice() {
    return productPrice.multiply(new BigDecimal(quantity));
  }
}
