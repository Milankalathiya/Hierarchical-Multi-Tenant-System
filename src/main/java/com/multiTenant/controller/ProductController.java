package com.multiTenant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.multiTenant.model.Product;
import com.multiTenant.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {
  @Autowired
  private ProductService productService;

  @GetMapping
  public List<Product> getProductsForUser(@RequestParam Long userId) {
    return productService.getProductsForUser(userId);
  }

  @PostMapping
  public Product createProduct(@RequestBody Product product) {
    return productService.saveProduct(product);
  }

  @GetMapping("/{id}")
  public Product getProductById(@PathVariable Long id) {
    return productService.getProductById(id);
  }

  @DeleteMapping("/{id}")
  public void deleteProduct(@PathVariable Long id) {
    productService.deleteProduct(id);
  }
}
