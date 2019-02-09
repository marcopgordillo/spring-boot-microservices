package com.example.inventoryservice.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "inventory")
public class InventoryItem {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "product_code", nullable = false, unique = true)
  private String productCode;

  @Column(name = "quantity")
  private Integer availableQuantity = 0;
}
