package com.example.productservice.controller;

import com.example.productservice.model.Product;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RestController
@RequestMapping("/products")
public class ProductController {

  private static final Logger logger = LogManager.getLogger(ProductController.class);

  @GetMapping("/{id}")
  public Product getProduct(@PathVariable Long id) {
    return new Product(id, "Chaise", 49.99);
  }

  @GetMapping
  public List<Product> getAllProducts() {
    logger.info("Entering getAllProducts method");
    try {
      List<Product> products = List.of(
          new Product(1L, "Chaise", 49.99),
          new Product(2L, "Table", 89.99));
      logger.info("Exiting getAllProducts method with result: {}", products);
      return products;
    } catch (Exception e) {
      logger.error("An error occurred in getAllProducts: {}", e.getMessage(), e);
      throw e;
    } finally {
      logger.warn("getAllProducts execution completed");
    }
  }
}
