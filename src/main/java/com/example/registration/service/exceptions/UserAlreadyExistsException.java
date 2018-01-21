package com.example.registration.service.exceptions;

import com.example.registration.service.domains.User;

/**
 * Business exception to be throw when te user is already registered
 *
 * @author Claudio Nazareth
 */
public class UserAlreadyExistsException extends RuntimeException {

  public UserAlreadyExistsException(User user) {
    super(String.format("User with username: %s already registered", user.getUsername()));
  }
}
