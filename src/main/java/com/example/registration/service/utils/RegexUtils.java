package com.example.registration.service.utils;

public class RegexUtils {

  // alphanumerical, no spaces
  public static final String USER_NAME_REGEX = "^[a-zA-Z0-9]*$";

  // at least four characters, at least one upper case character, at least one number
  public static final String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{4,}$";
}
