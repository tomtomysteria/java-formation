package com.example.springboot_demo.repository;

import com.example.springboot_demo.model.Product;
import com.example.springboot_demo.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
  List<Product> findByCategory(Category category);
}
