package com.example.springboot_demo.controller;

import com.example.springboot_demo.model.Category;
import com.example.springboot_demo.model.Product;
import com.example.springboot_demo.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

  private MockMvc mockMvc;

  @Mock
  private ProductService productService;

  @InjectMocks
  private ProductController productController;

  private static final Long PRODUCT_ID = 1L;
  private static final String PRODUCT_NAME = "Chaise";
  private static final double PRODUCT_PRICE = 59.99;
  private static final String PRODUCT_DESCRIPTION = "Comfortable chair";
  private static final int PRODUCT_STOCK = 10;

  private static final Long SECOND_PRODUCT_ID = 2L;
  private static final String SECOND_PRODUCT_NAME = "Table";
  private static final double SECOND_PRODUCT_PRICE = 89.99;
  private static final String SECOND_PRODUCT_DESCRIPTION = "Wooden table";
  private static final int SECOND_PRODUCT_STOCK = 5;

  private static final String CATEGORY_NAME = "Furniture";

  @Test
  void shouldReturnProductWhenIdExists() throws Exception {
    // Arrange
    mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    Product product = new Product(PRODUCT_ID, PRODUCT_NAME, PRODUCT_PRICE, PRODUCT_DESCRIPTION, PRODUCT_STOCK);
    when(productService.getProductById(PRODUCT_ID)).thenReturn(product);

    // Act & Assert
    mockMvc.perform(get("/produits/" + PRODUCT_ID))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.name").value(PRODUCT_NAME))
        .andExpect(jsonPath("$.description").value(PRODUCT_DESCRIPTION));
  }

  @Test
  void shouldReturnAllProducts() throws Exception {
    // Arrange
    mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    List<Product> products = List.of(
        new Product(PRODUCT_ID, PRODUCT_NAME, PRODUCT_PRICE, PRODUCT_DESCRIPTION, PRODUCT_STOCK),
        new Product(SECOND_PRODUCT_ID, SECOND_PRODUCT_NAME, SECOND_PRODUCT_PRICE, SECOND_PRODUCT_DESCRIPTION,
            SECOND_PRODUCT_STOCK));
    when(productService.getAllProducts()).thenReturn(products);

    // Act & Assert
    mockMvc.perform(get("/produits"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].name").value(PRODUCT_NAME))
        .andExpect(jsonPath("$[1].name").value(SECOND_PRODUCT_NAME));
  }

  @Test
  void shouldCreateProductSuccessfully() throws Exception {
    // Arrange
    mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    Product product = new Product(PRODUCT_ID, PRODUCT_NAME, PRODUCT_PRICE, PRODUCT_DESCRIPTION, PRODUCT_STOCK);
    when(productService.createProduct(any(Product.class))).thenReturn(product);

    ObjectMapper objectMapper = new ObjectMapper();
    String productJson = objectMapper.writeValueAsString(product);

    // Act & Assert
    mockMvc.perform(post("/produits")
        .contentType(MediaType.APPLICATION_JSON)
        .content(productJson))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.name").value(PRODUCT_NAME));
  }

  @Test
  void shouldDeleteProductSuccessfully() throws Exception {
    // Arrange
    mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    doNothing().when(productService).deleteProduct(PRODUCT_ID);

    // Act & Assert
    mockMvc.perform(delete("/produits/" + PRODUCT_ID))
        .andExpect(status().isOk());
  }

  @Test
  void shouldReturnProductsByCategory() throws Exception {
    // Arrange
    mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    Category category = new Category();
    category.setId(1L);
    category.setName(CATEGORY_NAME);

    Product product = new Product(PRODUCT_ID, PRODUCT_NAME, PRODUCT_PRICE, PRODUCT_DESCRIPTION, PRODUCT_STOCK);
    product.setCategory(category);

    when(productService.getProductsByCategory(1L)).thenReturn(List.of(product));

    // Act & Assert
    mockMvc.perform(get("/produits/par-categorie/1"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].name").value(PRODUCT_NAME));
  }
}
