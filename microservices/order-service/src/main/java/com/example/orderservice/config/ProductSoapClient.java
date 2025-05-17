package com.example.orderservice.config;

import org.springframework.ws.client.core.WebServiceTemplate;
import com.example.shared.soap.GetProductRequest;
import com.example.shared.soap.GetProductResponse;

public class ProductSoapClient {

  private final WebServiceTemplate webServiceTemplate;

  public ProductSoapClient(WebServiceTemplate webServiceTemplate) {
    this.webServiceTemplate = webServiceTemplate;
  }

  public GetProductResponse getProduct(long productId) {
    GetProductRequest request = new GetProductRequest();
    request.setId(productId);
    return (GetProductResponse) webServiceTemplate.marshalSendAndReceive(
        "http://localhost:8081/ws", request);
  }
}
