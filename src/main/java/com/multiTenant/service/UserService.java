package com.multiTenant.service;

import java.util.List;

import com.multiTenant.dto.LoginRequest;
import com.multiTenant.dto.RegisterRequest;
import com.multiTenant.model.User;
import com.multiTenant.service.JwtService;

public interface UserService {

  // User registerUser(RegisterRequest request);

  User getUserById(Long id);

  List<User> getAllUsersUnder(Long userId);

  boolean isUsernameOrEmailTaken(String username, String email);

  void registerUser(RegisterRequest request);

  String authenticateAndGenerateToken(LoginRequest request, JwtService jwtService);

}
