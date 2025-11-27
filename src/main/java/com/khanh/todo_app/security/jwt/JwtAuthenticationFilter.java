package com.khanh.todo_app.security.jwt;

import java.io.IOException;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
  private final UserDetails userDetails;
  private final JwtTokenProvider jwtTokenProvider;

  public JwtAuthenticationFilter(UserDetails userDetails, JwtTokenProvider jwtTokenProvider) {
    this.userDetails = userDetails;
    this.jwtTokenProvider = jwtTokenProvider;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    throw new UnsupportedOperationException("Unimplemented method 'doFilterInternal'");
  }
}
