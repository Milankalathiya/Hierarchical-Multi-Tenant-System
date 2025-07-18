package com.multiTenant.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.multiTenant.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByUsername(String username);

  List<User> findByParentId(Long parentId);

  boolean existsByUsername(String username);

  boolean existsByEmail(String email);

  Optional<User> findByEmail(String email);
}
