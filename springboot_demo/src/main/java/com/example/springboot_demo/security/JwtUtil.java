package com.example.springboot_demo.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtUtil {

  private static final Key SECRET_KEY = Keys
      .hmacShaKeyFor(Base64.getDecoder().decode("a2szUGcxWjwyYhtHdj09RzB9PC1YfHhJZGZPRHNaUkt1"));

  public String generateToken(String username) {
    return Jwts.builder()
        .setSubject(username)
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
