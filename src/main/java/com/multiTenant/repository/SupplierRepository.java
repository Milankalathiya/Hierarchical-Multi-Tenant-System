package com.multiTenant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.multiTenant.model.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
  List<Supplier> findByOwnerIdIn(List<Long> ownerIds);
}
