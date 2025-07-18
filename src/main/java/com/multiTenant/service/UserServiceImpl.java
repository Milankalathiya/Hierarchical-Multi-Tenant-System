package com.multiTenant.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.multiTenant.dto.LoginRequest;
import com.multiTenant.dto.RegisterRequest;
import com.multiTenant.model.Role;
import com.multiTenant.model.User;
import com.multiTenant.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private AuthenticationManager authenticationManager;

  @Override
  public void registerUser(RegisterRequest request) {
    if (userRepository.existsByEmail(request.email)) {
      throw new RuntimeException("Email already in use");
    }

    if (request.parentId != null && !userRepository.existsById(request.parentId)) {
      throw new RuntimeException("Invalid parent ID");
    }

    User user = new User();
    user.setUsername(request.username);
    user.setEmail(request.email);
    user.setPassword(passwordEncoder.encode(request.password));
    user.setParentId(request.parentId != null ? request.parentId : -1L);
    user.setRole(Role.valueOf(request.role.toUpperCase()));
    user.setCreatedAt(new Timestamp(System.currentTimeMillis()));

    userRepository.save(user);
  }

  public List<Long> getAllDescendantUserIds(Long userId) {
    List<Long> result = new ArrayList();
    result.add(userId);
    List<User> children = userRepository.findByParentId(userId);
    for (User child : children) {
      result.addAll(getAllDescendantUserIds(child.getId()));
    }
    return result;
  }

  @Override
  public User getUserById(Long id) {
    return userRepository.findById(id).orElse(null);
  }

  @Override
  public List<User> getAllUsersUnder(Long userId) {
    return List.of();
  }

  @Override
  public boolean isUsernameOrEmailTaken(String username, String email) {
    return userRepository.existsByUsername(username) || userRepository.existsByEmail(email);
  }

  @Override
  public String authenticateAndGenerateToken(LoginRequest request, JwtService jwtService) {
    User user = userRepository.findByUsername(request.usernameOrEmail)
        .or(() -> userRepository.findByEmail(request.usernameOrEmail))
        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    if (!passwordEncoder.matches(request.password, user.getPassword())) {
      throw new RuntimeException("Invalid credentials");
    }
    return jwtService.generateToken(user);
  }

}
