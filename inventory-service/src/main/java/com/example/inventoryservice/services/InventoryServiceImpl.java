package com.example.inventoryservice.services;

import com.example.inventoryservice.entities.InventoryItem;
import com.example.inventoryservice.repositories.InventoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryServiceImpl implements InventoryService {

  private final InventoryRepository inventoryRepository;

  public InventoryServiceImpl(InventoryRepository inventoryRepository) {
    this.inventoryRepository = inventoryRepository;
  }

  @Override
  public List<InventoryItem> findAllInventoryItems() {
    return inventoryRepository.findAll();
  }

  @Override
  public Optional<InventoryItem> findByProductCode(String productCode) {
    return inventoryRepository.findByProductCode(productCode);
  }
}
