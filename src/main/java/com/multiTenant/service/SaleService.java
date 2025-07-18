package com.multiTenant.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multiTenant.model.Sale;
import com.multiTenant.repository.SaleRepository;

@Service
public class SaleService {
  @Autowired
  private SaleRepository saleRepository;
  @Autowired
  private UserServiceImpl userService;

  public List<Sale> getSalesForUser(Long userId) {
    List<Long> allowedUserIds = userService.getAllDescendantUserIds(userId);
    return saleRepository.findByOwnerIdIn(allowedUserIds);
  }

  public Sale saveSale(Sale sale) {
    return saleRepository.save(sale);
  }

  public Sale getSaleById(Long id) {
    return saleRepository.findById(id).orElse(null);
  }

  public void deleteSale(Long id) {
    saleRepository.deleteById(id);
  }

  public List<Long> getAllowedUserIds(Long userId) {
    return userService.getAllDescendantUserIds(userId);
  }
}
