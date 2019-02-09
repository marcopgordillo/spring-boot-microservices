package com.example.inventoryservice.services;

import com.example.inventoryservice.entities.InventoryItem;

import java.util.List;
import java.util.Optional;

public interface InventoryService {

  List<InventoryItem> findAllInventoryItems();

  Optional<InventoryItem> findByProductCode(String productCode);
}
