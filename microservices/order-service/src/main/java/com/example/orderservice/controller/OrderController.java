package com.example.orderservice.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import com.example.orderservice.config.ProductSoapClient;
import com.example.shared.soap.GetProductResponse;
import java.util.Map;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

  private final RestTemplate restTemplate;
  private final ProductSoapClient productSoapClient;

  public OrderController(RestTemplate restTemplate, ProductSoapClient productSoapClient) {
    this.restTemplate = restTemplate;
    this.productSoapClient = productSoapClient;
  }

  @GetMapping("/create/{productId}")
  public Map<String, Object> createOrder(@PathVariable Long productId) {
    String productServiceUrl = "http://localhost:8081/products/" + productId;
    Object product = restTemplate.getForObject(productServiceUrl, Object.class);

    return Map.of(
        "status", "Commande créée",
        "product", product);
  }

  @GetMapping("/test-soap/{productId}")
  public ResponseEntity<GetProductResponse> testSoapClient(@PathVariable Long productId) {
    GetProductResponse response = productSoapClient.getProduct(productId);
    return ResponseEntity.ok(response);
  }

  @GetMapping("/{id}")
  public Map<String, Object> getOrder(@PathVariable Long id) {
    // Exemple de réponse fictive
    return Map.of(
        "orderId", id,
        "status", "Commande récupérée",
        "details", "Détails fictifs de la commande");
  }

  @GetMapping
  public List<Map<String, Object>> getAllOrders() {
    return List.of(
        Map.of("orderId", 1, "product", "Chaise", "quantity", 2),
        Map.of("orderId", 2, "product", "Table", "quantity", 1));
  }
}
