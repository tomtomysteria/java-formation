package com.example.springboot_demo.controller;

import com.example.springboot_demo.model.Category;
import com.example.springboot_demo.model.Product;
import com.example.springboot_demo.repository.CategoryRepository;
import com.example.springboot_demo.repository.ProductRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produits")
public class ProductController {

  private final ProductRepository repo;
  private final CategoryRepository categoryRepo;

  public ProductController(ProductRepository repo, CategoryRepository categoryRepo) {
    this.repo = repo;
    this.categoryRepo = categoryRepo;
  }

  @GetMapping
  public List<Product> getAll() {
    return repo.findAll();
  }

  @PostMapping
  public Product create(@RequestBody Product p) {
    if (p.getCategory() != null && p.getCategory().getId() != null) {
      Category category = categoryRepo.findById(p.getCategory().getId()).orElse(null);
      p.setCategory(category);
    }
    return repo.save(p);
  }

  @GetMapping("/{id}")
  public Product getById(@PathVariable Long id) {
    return repo.findById(id).orElse(null);
  }

  @PutMapping("/{id}")
  public Product update(@PathVariable Long id, @RequestBody Product updated) {
    updated.setId(id);
    return repo.save(updated);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id) {
    repo.deleteById(id);
  }
}
