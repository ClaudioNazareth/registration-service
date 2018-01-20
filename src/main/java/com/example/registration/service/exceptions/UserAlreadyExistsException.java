package com.example.registration.service.exceptions;

import com.example.registration.service.domains.User;

public class UserAlreadyExistsException extends RuntimeException {

  public UserAlreadyExistsException(User user) {
    super(String.format("User with username: %s already registered", user.getUsername()));
  }
}
