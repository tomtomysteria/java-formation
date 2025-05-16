package com.example.springboot_demo.controller;

import com.example.springboot_demo.model.Article;
import com.example.springboot_demo.repository.ArticleRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/articles")
public class ArticleController {

  private final ArticleRepository repo;

  public ArticleController(ArticleRepository repo) {
    this.repo = repo;
  }

  @GetMapping
  public List<Article> getAll() {
    return repo.findAll();
  }

  @PostMapping
  public Article create(@RequestBody Article article) {
    return repo.save(article);
  }

  @GetMapping("/{id}")
  public Article getById(@PathVariable String id) {
    return repo.findById(id).orElse(null);
  }

  @PutMapping("/{id}")
  public Article update(@PathVariable String id, @RequestBody Article updated) {
    updated.setId(id);
    return repo.save(updated);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable String id) {
    repo.deleteById(id);
  }

  @GetMapping("/auteur/{nom}")
  public List<Article> getByAuthor(@PathVariable String nom) {
    return repo.findByAuthor(nom);
  }
}
