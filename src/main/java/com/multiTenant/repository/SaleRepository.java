package com.multiTenant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.multiTenant.model.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long> {
  List<Sale> findByOwnerIdIn(List<Long> ownerIds);
}
