package com.example.productservice.soap;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class CategorySoapEndpoint {

  private static final String NAMESPACE_URI = "http://example.com/category";

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetCategoryRequest")
  @ResponsePayload
  public GetCategoryResponse getCategory(@RequestPayload GetCategoryRequest request) {
    GetCategoryResponse response = new GetCategoryResponse();
    response.setId(1L);
    response.setName("Sample Category");
    return response;
  }
}
