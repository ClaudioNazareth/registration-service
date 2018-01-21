package com.example.registration.service.exceptions;

import com.example.registration.service.domains.User;

/**
 * Business exception to be throw when te user is in the black list.
 *
 * @author Claudio Nazareth
 */
public class UserInBlackListException extends RuntimeException {

  public UserInBlackListException(User user) {
    super(String.format("User in black list can not be registered.  %s", user));
  }
}
