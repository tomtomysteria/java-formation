package com.example.productservice.controller;

import com.example.productservice.model.Product;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

  @GetMapping("/{id}")
  public Product getProduct(@PathVariable Long id) {
    return new Product(id, "Chaise", 49.99);
  }
}
