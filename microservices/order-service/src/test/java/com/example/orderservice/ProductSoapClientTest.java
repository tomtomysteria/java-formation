package com.example.orderservice;

import com.example.orderservice.config.ProductSoapClient;
import com.example.shared.soap.GetProductResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ws.client.core.WebServiceTemplate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ProductSoapClientTest {

  @Autowired
  private WebServiceTemplate webServiceTemplate;

  @Test
  public void testGetProduct() {
    ProductSoapClient client = new ProductSoapClient(webServiceTemplate);
    GetProductResponse response = client.getProduct(1L);

    assertNotNull(response);
    assertEquals(1L, response.getId());
    assertEquals("Sample Product", response.getName());
    assertEquals(99.99, response.getPrice());
  }
}
