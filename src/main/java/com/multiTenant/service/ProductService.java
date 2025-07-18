package com.multiTenant.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multiTenant.model.Product;
import com.multiTenant.repository.ProductRepository;

@Service
public class ProductService {
  @Autowired
  private ProductRepository productRepository;
  @Autowired
  private UserServiceImpl userService;

  public List<Product> getProductsForUser(Long userId) {
    List<Long> allowedUserIds = userService.getAllDescendantUserIds(userId);
    return productRepository.findByOwnerIdIn(allowedUserIds);
  }

  public Product saveProduct(Product product) {
    return productRepository.save(product);
  }

  public Product getProductById(Long id) {
    return productRepository.findById(id).orElse(null);
  }

  public void deleteProduct(Long id) {
    productRepository.deleteById(id);
  }
}
