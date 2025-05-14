package com.example.springboot_demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

  @GetMapping("/product")
  public Product getProduct() {
    return new Product("Switch 2", 449.99);
  }

}
