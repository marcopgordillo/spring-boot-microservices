package com.example.catalogservice.services;

import com.example.catalogservice.utils.MyThreadLocalsHolder;
import com.example.catalogservice.web.models.ProductInventoryResponse;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
public class InventoryServiceClientImpl implements InventoryServiceClient {

  private static final String INVENTORY_API_PATH = "http://inventory-service/api/";

  private final RestTemplate restTemplate;

  public InventoryServiceClientImpl(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  @Override
  public List<ProductInventoryResponse> getProductInventoryLevels() {
    return null;
  }

  @Override
  public List<ProductInventoryResponse> getDefaultProductInventoryLevels() {
    return null;
  }

  @Override
  @HystrixCommand(commandKey = "inventory-by-productcode", fallbackMethod = "getDefaultProductInventoryByCode")
  public Optional<ProductInventoryResponse> getProductInventoryByCode(String productCode) {

    log.info("CorrelationID: " + MyThreadLocalsHolder.getCorrelationId());

    ResponseEntity<ProductInventoryResponse> itemResponseEntity = restTemplate.getForEntity(INVENTORY_API_PATH + "inventory/{code}", ProductInventoryResponse.class, productCode);

    if (itemResponseEntity.getStatusCode() == HttpStatus.OK) {
      Integer quantity = Objects.requireNonNull(itemResponseEntity.getBody()).getAvailableQuantity();
      log.info("Available quantity: " + quantity);
      return Optional.ofNullable(itemResponseEntity.getBody());
    } else {
      log.error("Unable to get inventory level for product_code: " + productCode + ", StatusCode: " + itemResponseEntity.getStatusCode());
      return Optional.empty();
    }
  }

  @Override
  @SuppressWarnings("unused")
  public Optional<ProductInventoryResponse> getDefaultProductInventoryByCode(String productCode) {

    log.info("Returning default ProductInventoryByCode for productCode: " + productCode);
    log.info("CorrelationID: " + MyThreadLocalsHolder.getCorrelationId());

    ProductInventoryResponse response = new ProductInventoryResponse();
    response.setProductCode(productCode);
    response.setAvailableQuantity(50);
    log.info("Available quantity: " + response.getAvailableQuantity());
    return Optional.of(response);
  }
}
