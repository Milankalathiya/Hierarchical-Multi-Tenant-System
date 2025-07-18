package com.multiTenant.service;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.multiTenant.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
  private static final String SECRET = "b7f8e2c1a9d4e5f6c3b2a1e4d7c6b5a8e9f0c2d1b3a4e6f7d8c9b0a1e2f3c4d5";
  private static final long EXPIRATION = 1000 * 60 * 60 * 24;
  private final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());

  public String generateToken(User user) {
    return Jwts.builder()
        .setSubject(user.getId().toString())
        .claim("username", user.getUsername())
        .claim("role", user.getRole().name())
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
        .signWith(key, SignatureAlgorithm.HS256)
        .compact();
  }

  public Jws<Claims> validateToken(String token) {
    return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
  }

  public Long getUserIdFromToken(String token) {
    return Long.parseLong(validateToken(token).getBody().getSubject());
  }
}
