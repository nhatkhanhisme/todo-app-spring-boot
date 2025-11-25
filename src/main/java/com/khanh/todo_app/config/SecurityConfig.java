package com.khanh.todo_app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity // Kich hoat tinh nang bao mat web cua Spring
public class SecurityConfig {
  // 1. Dinh nghia chuoi filter bao mat
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .csrf(csrf -> csrf.disable()) // Vo hieu hoa CSRF de test bang Postman
        .httpBasic(basic -> basic.disable()) // Vo hieu hoa Basic Auth
        .formLogin(form -> form.disable()) // Vo hieu hoa Form Login
        .authorizeHttpRequests(auth -> auth
            // Dung duong dan nay cho Login/Register ma khong can xac thuc
            .requestMatchers("/api/v1/auth/**", "/error").permitAll() // Cho phep tat ca cac yeu cau truy cap ma khong
                                                                      // can xac thuc
            .anyRequest().authenticated() // Bat buoc xac thuc voi cac yeu cau con lai
        );
    return http.build();
  }

  // 2. Dinh nghia thuat toan ma hoa mat khau (BCryptPasswordEncoder)
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
    return configuration.getAuthenticationManager();
  }
}
