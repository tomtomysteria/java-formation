package com.example.springboot_demo.service;

import com.example.springboot_demo.model.Category;
import com.example.springboot_demo.model.Product;
import com.example.springboot_demo.repository.CategoryRepository;
import com.example.springboot_demo.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

  @Mock
  private ProductRepository productRepository;

  @Mock
  private CategoryRepository categoryRepository;

  @InjectMocks
  private ProductService productService;

  private static final Long PRODUCT_ID = 1L;
  private static final String PRODUCT_NAME = "Chaise";
  private static final double PRODUCT_PRICE = 59.99;
  private static final String PRODUCT_DESCRIPTION = "Comfortable chair";
  private static final int PRODUCT_STOCK = 10;

  private static final Long UPDATED_PRODUCT_ID = 2L;
  private static final String UPDATED_PRODUCT_NAME = "Table";
  private static final double UPDATED_PRODUCT_PRICE = 89.99;
  private static final String UPDATED_PRODUCT_DESCRIPTION = "Wooden table";
  private static final int UPDATED_PRODUCT_STOCK = 5;

  private static final Long CATEGORY_ID = 1L;
  private static final String CATEGORY_NAME = "Furniture";

  @Test
  void shouldReturnProductWhenIdExists() {
    // Arrange
    Product product = new Product(PRODUCT_ID, PRODUCT_NAME, PRODUCT_PRICE, PRODUCT_DESCRIPTION, PRODUCT_STOCK);
    when(productRepository.findById(PRODUCT_ID)).thenReturn(Optional.of(product));

    // Act
    Product result = productService.getProductById(PRODUCT_ID);

    // Assert
    assertNotNull(result);
    assertEquals(PRODUCT_NAME, result.getName());
    verify(productRepository, times(1)).findById(PRODUCT_ID);
    verifyNoMoreInteractions(productRepository);
  }

  @Test
  void shouldReturnNullWhenIdDoesNotExist() {
    // Arrange
    when(productRepository.findById(PRODUCT_ID)).thenReturn(Optional.empty());

    // Act
    Product result = productService.getProductById(PRODUCT_ID);

    // Assert
    assertNull(result);
    verify(productRepository, times(1)).findById(PRODUCT_ID);
    verifyNoMoreInteractions(productRepository);
  }

  @Test
  void shouldCreateProductSuccessfully() {
    // Arrange
    Product product = new Product(PRODUCT_ID, PRODUCT_NAME, PRODUCT_PRICE, PRODUCT_DESCRIPTION, PRODUCT_STOCK);
    when(productRepository.save(product)).thenReturn(product);

    // Act
    Product result = productService.createProduct(product);

    // Assert
    assertNotNull(result);
    assertEquals(PRODUCT_NAME, result.getName());
    verify(productRepository, times(1)).save(product);
    verifyNoMoreInteractions(productRepository);
  }

  @Test
  void shouldUpdateProductSuccessfully() {
    // Arrange
    Product updatedProduct = new Product(UPDATED_PRODUCT_ID, UPDATED_PRODUCT_NAME, UPDATED_PRODUCT_PRICE,
        UPDATED_PRODUCT_DESCRIPTION, UPDATED_PRODUCT_STOCK);
    when(productRepository.save(updatedProduct)).thenReturn(updatedProduct);

    // Act
    Product result = productService.updateProduct(UPDATED_PRODUCT_ID, updatedProduct);

    // Assert
    assertNotNull(result);
    assertEquals(UPDATED_PRODUCT_NAME, result.getName());
    verify(productRepository, times(1)).save(updatedProduct);
    verifyNoMoreInteractions(productRepository);
  }

  @Test
  void shouldDeleteProductSuccessfully() {
    // Arrange
    doNothing().when(productRepository).deleteById(PRODUCT_ID);

    // Act
    productService.deleteProduct(PRODUCT_ID);

    // Assert
    verify(productRepository, times(1)).deleteById(PRODUCT_ID);
    verifyNoMoreInteractions(productRepository);
  }

  @Test
  void shouldReturnProductsByCategory() {
    // Arrange
    Category category = new Category();
    category.setId(CATEGORY_ID);
    category.setName(CATEGORY_NAME);

    Product product = new Product(PRODUCT_ID, PRODUCT_NAME, PRODUCT_PRICE, PRODUCT_DESCRIPTION, PRODUCT_STOCK);
    product.setCategory(category);

    when(categoryRepository.findById(CATEGORY_ID)).thenReturn(Optional.of(category));
    when(productRepository.findByCategory(category)).thenReturn(List.of(product));

    // Act
    List<Product> result = productService.getProductsByCategory(CATEGORY_ID);

    // Assert
    assertNotNull(result);
    assertEquals(1, result.size());
    assertEquals(PRODUCT_NAME, result.get(0).getName());
    verify(categoryRepository, times(1)).findById(CATEGORY_ID);
    verify(productRepository, times(1)).findByCategory(category);
    verifyNoMoreInteractions(categoryRepository, productRepository);
  }
}
