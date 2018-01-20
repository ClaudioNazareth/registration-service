package com.example.registration.service.utils;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtilsTest {

  @Test
  public void when_string_is_not_null_or_empty_must_encrypt() {
    String password = "2wsxcde3";

    final String passwordBCrypt = PasswordUtils.generateBCrypt(password);
    final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    assertThat(passwordEncoder.matches(password, passwordBCrypt))
        .as("The password must match")
        .isTrue();
  }

  @Test
  public void when_string_is_null_must_return_it() {
    final String passwordBCrypt = PasswordUtils.generateBCrypt(null);
    assertThat(passwordBCrypt).as("The return must be null").isNull();
  }

  @Test
  public void when_string_is_empty_must_return_it() {
    final String passwordBCrypt = PasswordUtils.generateBCrypt("");
    assertThat(passwordBCrypt).as("The return must be empty").isEmpty();
  }
}
