package com.example.inventoryservice.web.controllers;

import com.example.inventoryservice.entities.InventoryItem;
import com.example.inventoryservice.services.InventoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

  private final InventoryService inventoryService;

  public InventoryController(InventoryService inventoryService) {
    this.inventoryService = inventoryService;
  }

  @GetMapping("{productCode}")
  public ResponseEntity<Optional<InventoryItem>> findInventoryByProductCode(@PathVariable("productCode") String productCode) {
    log.info("Finding inventory for product code :"+productCode);
    Optional<InventoryItem> inventoryItem = inventoryService.findByProductCode(productCode);

    if(inventoryItem.isPresent()) {
      return new ResponseEntity<>(inventoryItem, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

}
