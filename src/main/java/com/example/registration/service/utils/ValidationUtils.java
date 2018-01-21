package com.example.registration.service.utils;

/**
 * Contains regex and its validation messages
 *
 * @author Claudio Nazareth
 */
public class ValidationUtils {

  // alphanumerical, no spaces
  public static final String USER_NAME_REGEX = "^[a-zA-Z0-9]*$";

  public static final String USER_NAME_VALIDATION_MESSAGE =
      "The username must be alphanumerical, no spaces";

  // at least four characters, at least one upper case character, at least one number
  public static final String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{4,}$";

  public static final String PASSWORD_VALIDATION_MESSAGE =
      "The password must be at least four characters, at least one upper case character, at least one number";
}
