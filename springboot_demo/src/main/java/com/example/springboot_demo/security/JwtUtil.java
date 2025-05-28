package com.example.springboot_demo.security;

import com.example.springboot_demo.model.Role;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Component
public class JwtUtil {

  private static final Key SECRET_KEY = Keys
      .hmacShaKeyFor(Base64.getDecoder().decode("a2szUGcxWjwyYhtHdj09RzB9PC1YfHhJZGZPRHNaUkt1"));

  public String generateToken(String username, Set<Role> roles) {
    List<String> roleNames = roles.stream()
        .map(Role::getName)
        .toList();
    return Jwts.builder()
        .setSubject(username)
        .claim("roles", roleNames) // Ajouter les noms des rôles comme claim
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1h
        .signWith(SECRET_KEY)
        .compact();
  }

  public String extractUsername(String token) {
    return Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token).getBody().getSubject();
  }

  public boolean validateToken(String token, UserDetails userDetails) {
    final String username = extractUsername(token);
    return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
  }

  private boolean isTokenExpired(String token) {
    final Date expiration = Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token).getBody()
        .getExpiration();
    return expiration.before(new Date());
  }
}
