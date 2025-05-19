package com.example.productservice.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RestController
@RequestMapping("/rest-wsdl")
public class WsdlController {

  private static final Logger logger = LogManager.getLogger(WsdlController.class);

  @GetMapping("/test")
  public String test() {
    logger.debug("Received request for test endpoint at /rest-wsdl/test");
    return "Test endpoint for WSDL";
  }

  @GetMapping("/product.xsd")
  public ResponseEntity<Resource> getXsd() {
    logger.debug("Received request for XSD at /rest-wsdl/product.xsd");
    ClassPathResource xsdResource = new ClassPathResource("product.xsd");
    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=product.xsd")
        .contentType(MediaType.APPLICATION_XML)
        .body(xsdResource);
  }

  @GetMapping("/product.wsdl")
  public ResponseEntity<Resource> getWsdl() {
    logger.debug("Received request for WSDL at /rest-wsdl/product.wsdl");
    ClassPathResource wsdlResource = new ClassPathResource("product.wsdl"); // Match target/classes location
    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=product.wsdl")
        .contentType(MediaType.APPLICATION_XML)
        .body(wsdlResource);
  }
}
