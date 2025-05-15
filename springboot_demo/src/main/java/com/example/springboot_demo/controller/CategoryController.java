package com.example.springboot_demo.controller;

import com.example.springboot_demo.model.Category;
import com.example.springboot_demo.repository.CategoryRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

  private final CategoryRepository repo;

  public CategoryController(CategoryRepository repo) {
    this.repo = repo;
  }

  @GetMapping
  public List<Category> getAll() {
    return repo.findAll();
  }

  @PostMapping
  public Category create(@RequestBody Category category) {
    return repo.save(category);
  }

  @GetMapping("/{id}")
  public Category getById(@PathVariable Long id) {
    return repo.findById(id).orElse(null);
  }

  @PutMapping("/{id}")
  public Category update(@PathVariable Long id, @RequestBody Category updated) {
    updated.setId(id);
    return repo.save(updated);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id) {
    repo.deleteById(id);
  }
}
