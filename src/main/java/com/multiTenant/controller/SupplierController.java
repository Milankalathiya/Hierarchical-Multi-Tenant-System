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

import com.multiTenant.model.Supplier;
import com.multiTenant.service.SupplierService;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {
  @Autowired
  private SupplierService supplierService;

  @GetMapping
  public List<Supplier> getSuppliersForUser(@RequestParam Long userId) {
    return supplierService.getSuppliersForUser(userId);
  }

  @PostMapping
  public Supplier createSupplier(@RequestBody Supplier supplier) {
    return supplierService.saveSupplier(supplier);
  }

  @GetMapping("/{id}")
  public Supplier getSupplierById(@PathVariable Long id) {
    return supplierService.getSupplierById(id);
  }

  @DeleteMapping("/{id}")
  public void deleteSupplier(@PathVariable Long id) {
    supplierService.deleteSupplier(id);
  }
}
