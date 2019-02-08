package com.example.catalogservice.entities;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name = "products")
public class Product {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String code;

    @Column(nullable = false)
    private String name;

    private String description;

    private Double price;

    @Transient
    private boolean inStock = true;
}
