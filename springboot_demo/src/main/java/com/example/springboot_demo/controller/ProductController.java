package com.example.springboot_demo.controller;

import com.example.springboot_demo.model.Product;
import com.example.springboot_demo.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produits")
public class ProductController {

  private final ProductService productService;

  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping
  public List<Product> getAll() {
    return productService.getAllProducts();
  }

  @PostMapping
  public Product create(@Valid @RequestBody Product p) {
    return productService.createProduct(p);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Product> getById(@PathVariable Long id) {
    Product product = productService.getProductById(id);
    if (product == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(product);
  }

  @PutMapping("/{id}")
  public Product update(@PathVariable Long id, @RequestBody Product updated) {
    return productService.updateProduct(id, updated);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id) {
    productService.deleteProduct(id);
  }

  @GetMapping("/par-categorie/{id}")
  public List<Product> getByCategory(@PathVariable Long id) {
    return productService.getProductsByCategory(id);
  }
}
