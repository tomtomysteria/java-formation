package com.example.productservice.soap;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class ProductSoapEndpoint {

  private static final String NAMESPACE_URI = "http://example.com/product";

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetProductRequest")
  @ResponsePayload
  public GetProductResponse getProduct(@RequestPayload GetProductRequest request) {
    GetProductResponse response = new GetProductResponse();
    response.setId(1L);
    response.setName("Sample Product");
    response.setPrice(99.99);
    return response;
  }
}
