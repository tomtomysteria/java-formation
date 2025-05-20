package com.example.springboot_demo.service;

import com.example.springboot_demo.model.Category;
import com.example.springboot_demo.model.Product;
import com.example.springboot_demo.repository.CategoryRepository;
import com.example.springboot_demo.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ProductService {

  private final ProductRepository productRepository;
  private final CategoryRepository categoryRepository;

  public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
    this.productRepository = productRepository;
    this.categoryRepository = categoryRepository;
  }

  public List<Product> getAllProducts() {
    return productRepository.findAll();
  }

  public Product createProduct(Product product) {
    if (product.getCategory() != null && product.getCategory().getId() != null) {
      Category category = categoryRepository.findById(product.getCategory().getId()).orElse(null);
      product.setCategory(category);
    }
    return productRepository.save(product);
  }

  public Product getProductById(Long id) {
    return productRepository.findById(id).orElse(null);
  }

  public Product updateProduct(Long id, Product updatedProduct) {
    updatedProduct.setId(id);
    return productRepository.save(updatedProduct);
  }

  public void deleteProduct(Long id) {
    productRepository.deleteById(id);
  }

  public List<Product> getProductsByCategory(Long categoryId) {
    Category category = categoryRepository.findById(categoryId).orElse(null);
    if (category != null) {
      return productRepository.findByCategory(category);
    }
    return Collections.emptyList();
  }
}
