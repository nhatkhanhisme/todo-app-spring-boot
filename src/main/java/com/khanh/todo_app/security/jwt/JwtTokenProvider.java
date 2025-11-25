package com.khanh.todo_app.security.jwt; // Tạo package jwt

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

// responsible for generating and validating JWT tokens
@Component
public class JwtTokenProvider {
  // get secret key and expiration time from application properties
  @Value("${app.jwt.secret}")
  private String jwtSecret;

  @Value("${jwt.expiration-milliseconds}")
  private long jwtExpirationDate;

  // get signing key from secret
  // Decode the base64 encoded secret to get the byte array
  // Then use Keys.hmacShaKeyFor to generate the signing key
  // This key will be used to sign and verify JWT tokens
  // This method is private because it is only used within this class
  // It is not exposed to other classes
  // It is called whenever we need to sign or verify a JWT token
  private Key key() {
    return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
  }

  // Generate JWT token from authentication object
  public String generateToken(Authentication authentication) {
    String username = authentication.getName();
    Date now = new Date();
    Date expirDate = new Date(now.getTime() + jwtExpirationDate);
    return Jwts.builder()
        .setSubject(username) // set username as subject
        .setIssuedAt(now)
        .setExpiration(expirDate)
        .signWith(key(), SignatureAlgorithm.HS256) // sign the token with the key and algorithm
        .compact();
  }

  // Get username from JWT token
  public String getUsernameFromJWT(String token) {
    Claims claims = Jwts.parserBuilder()
        .setSigningKey(key())
        .build()
        .parseClaimsJws(token)
        .getBody();
    return claims.getSubject();
  }

  // Validate JWT token
  public boolean validateToken(String token) {
    try {
      //
      Jwts.parserBuilder().setSigningKey(key()).build().parseClaimsJws(token);
      return true;
    } catch (MalformedJwtException ex) {
      throw new MalformedJwtException("Invalid JWT token");
    } catch (ExpiredJwtException ex) {
      throw new ExpiredJwtException(null, null, "Expired JWT token");
    } catch (UnsupportedJwtException ex) {
      throw new UnsupportedJwtException("Unsupported JWT token");
    } catch (IllegalArgumentException ex) {
      throw new IllegalArgumentException("JWT claims string is empty.");
    }
  }
}
