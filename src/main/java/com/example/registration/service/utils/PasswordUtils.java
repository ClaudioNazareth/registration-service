package com.example.registration.service.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtils {

  public static String generateBCrypt(String password) {

    if (password == null || password.isEmpty()) return password;

    return new BCryptPasswordEncoder().encode(password);
  }
}
