package com.example.registration.service.utils;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.junit.Test;

public class RegexUtilsTest {

  private static final String PASSWORD_MESSAGE =
      "The password must contains at least four characters, at least one upper case character, at least one number";

  private static final String USERNAME_MESSAGE = "The username must be alphanumerical, no spaces";

  private static final String CORRECT_PASSWORD = "Gamesys1";
  private static final String PASSWORD_NO_UPPER_CASE = "gamesys1";
  private static final String PASSWORD_NO_NUMBER = "Gamesys";
  private static final String PASSWORD_LESS_THAN_FOUR = "Sy1";

  private static final String CORRECT_USERNAME = "claudio";

  private static final String USERNAME_WITH_SPACE = "claudio nazareth";

  @Test
  public void when_password_match_regex_return_should_return_true() {
    Pattern pattern = Pattern.compile(RegexUtils.PASSWORD_REGEX);
    final Matcher matcher = pattern.matcher(CORRECT_PASSWORD);
    assertThat(matcher.find()).as(PASSWORD_MESSAGE).isTrue();
  }

  @Test
  public void when_password_with_no_upper_case_should_return_false() {
    Pattern pattern = Pattern.compile(RegexUtils.PASSWORD_REGEX);
    final Matcher matcher = pattern.matcher(PASSWORD_NO_UPPER_CASE);
    assertThat(matcher.find()).as(PASSWORD_MESSAGE).isFalse();
  }

  @Test
  public void when_password_with_no_number_should_return_false() {
    Pattern pattern = Pattern.compile(RegexUtils.PASSWORD_REGEX);
    final Matcher matcher = pattern.matcher(PASSWORD_NO_NUMBER);
    assertThat(matcher.find()).as(PASSWORD_MESSAGE).isFalse();
  }

  @Test
  public void when_password_less_than_four_should_return_false() {
    Pattern pattern = Pattern.compile(RegexUtils.PASSWORD_REGEX);
    final Matcher matcher = pattern.matcher(PASSWORD_LESS_THAN_FOUR);
    assertThat(matcher.find()).as(PASSWORD_MESSAGE).isFalse();
  }

  @Test
  public void when_username_match_regex_should_return_true() {
    Pattern pattern = Pattern.compile(RegexUtils.USER_NAME_REGEX);
    final Matcher matcher = pattern.matcher(CORRECT_USERNAME);
    assertThat(matcher.find()).as(USERNAME_MESSAGE).isTrue();
  }

  @Test
  public void when_username_with_space_should_return_false() {
    Pattern pattern = Pattern.compile(RegexUtils.USER_NAME_REGEX);
    final Matcher matcher = pattern.matcher(USERNAME_WITH_SPACE);
    assertThat(matcher.find()).as(USERNAME_MESSAGE).isFalse();
  }
}
