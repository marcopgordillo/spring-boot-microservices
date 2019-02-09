package com.example.catalogservice.services;

import com.example.catalogservice.web.models.ProductInventoryResponse;

import java.util.List;
import java.util.Optional;

public interface InventoryServiceClient {

  List<ProductInventoryResponse> getProductInventoryLevels();

  List<ProductInventoryResponse> getDefaultProductInventoryLevels();

  Optional<ProductInventoryResponse> getProductInventoryByCode(String productCode);

  Optional<ProductInventoryResponse> getDefaultProductInventoryByCode(String productCode);
}
