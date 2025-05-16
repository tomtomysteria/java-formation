package com.example.springboot_demo.repository;

import com.example.springboot_demo.model.Article;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ArticleRepository extends MongoRepository<Article, String> {
  List<Article> findByAuthor(String author);
}
