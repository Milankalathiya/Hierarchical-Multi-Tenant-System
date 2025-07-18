package com.multiTenant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.multiTenant.model.Sale;
import com.multiTenant.model.User;
import com.multiTenant.service.SaleService;

@RestController
@RequestMapping("/api/sales")
public class SaleController {
  @Autowired
  private SaleService saleService;

  @GetMapping
  public List<Sale> getSalesForUser(@RequestParam Long userId) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    User currentUser = (User) authentication.getPrincipal();
    List<Long> allowedUserIds = saleService.getAllowedUserIds(currentUser.getId());
    if (!allowedUserIds.contains(userId)) {
      throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Not allowed to access this user's sales");
    }
    return saleService.getSalesForUser(userId);
  }

  @PostMapping
  public Sale createSale(@RequestBody Sale sale) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    User currentUser = (User) authentication.getPrincipal();
    List<Long> allowedUserIds = saleService.getAllowedUserIds(currentUser.getId());
    if (!allowedUserIds.contains(sale.getOwnerId())) {
      throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Not allowed to create sale for this user");
    }
    return saleService.saveSale(sale);
  }

  @GetMapping("/{id}")
  public Sale getSaleById(@PathVariable Long id) {
    return saleService.getSaleById(id);
  }

  @DeleteMapping("/{id}")
  public void deleteSale(@PathVariable Long id) {
    saleService.deleteSale(id);
  }
}
