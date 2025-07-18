package com.multiTenant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.multiTenant.dto.LoginRequest;
import com.multiTenant.dto.RegisterRequest;
import com.multiTenant.service.JwtService;
import com.multiTenant.service.UserServiceImpl;

@RestController
@RequestMapping("/api/users")
public class UserController {

  @Autowired
  private UserServiceImpl userService;

  @Autowired
  private JwtService jwtService;

  @PostMapping("/register")
  public ResponseEntity<String> registerUser(@RequestBody RegisterRequest request) {
    userService.registerUser(request);
    return ResponseEntity.ok("User registered successfully");
  }

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody LoginRequest request) {
    String token = userService.authenticateAndGenerateToken(request, jwtService);
    return ResponseEntity.ok(token);
  }

}
