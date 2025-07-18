package com.multiTenant.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multiTenant.model.Supplier;
import com.multiTenant.repository.SupplierRepository;

@Service
public class SupplierService {
  @Autowired
  private SupplierRepository supplierRepository;
  @Autowired
  private UserServiceImpl userService;

  public List<Supplier> getSuppliersForUser(Long userId) {
    List<Long> allowedUserIds = userService.getAllDescendantUserIds(userId);
    return supplierRepository.findByOwnerIdIn(allowedUserIds);
  }

  public Supplier saveSupplier(Supplier supplier) {
    return supplierRepository.save(supplier);
  }

  public Supplier getSupplierById(Long id) {
    return supplierRepository.findById(id).orElse(null);
  }

  public void deleteSupplier(Long id) {
    supplierRepository.deleteById(id);
  }
}
