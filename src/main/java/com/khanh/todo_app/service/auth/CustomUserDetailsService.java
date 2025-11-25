package com.khanh.todo_app.service.auth;

import com.khanh.todo_app.model.User;
import com.khanh.todo_app.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.Arrays;

@Service
public class CustomUserDetailsService implements UserDetailsService {

  private final UserRepository userRepository;

  public CustomUserDetailsService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  // Spring Security gọi hàm này khi cần tải thông tin User (dùng username)
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    // 1. Tìm User trong Database
    User user = userRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("Không tìm thấy người dùng với username: " + username));

    // 2. Chuyển đổi Roles (String) thành GrantedAuthority (Chuẩn của Spring
    // Security)
    // Hiện tại bạn đang lưu roles là String (ví dụ: "USER"), nên phải tách nó ra.
    Set<GrantedAuthority> authorities = Arrays.stream(user.getRole().split(","))
        .map(role -> new SimpleGrantedAuthority(role.trim()))
        .collect(Collectors.toSet());

    // 3. Trả về đối tượng UserDetails (đối tượng mà Spring Security sử dụng nội bộ)
    return new org.springframework.security.core.userdetails.User(
        user.getUsername(),
        user.getPassword(), // Mật khẩu đã mã hóa BCrypt
        authorities);
  }
}
