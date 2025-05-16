package com.example.springboot_demo.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "articles")
public class Article {

  @Id
  private String id;

  private String title;
  private String content;
  private String author;

  @CreatedDate
  private LocalDateTime publishedAt;

  // Constructors
  public Article() {
  }

  public Article(String id, String title, String content, String author, LocalDateTime publishedAt) {
    this.id = id;
    this.title = title;
    this.content = content;
    this.author = author;
    this.publishedAt = publishedAt;
  }

  // Getters & Setters
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public LocalDateTime getPublishedAt() {
    return publishedAt;
  }

  public void setPublishedAt(LocalDateTime publishedAt) {
    this.publishedAt = publishedAt;
  }
}
