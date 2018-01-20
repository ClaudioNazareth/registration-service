package com.example.registration.service.fixtures;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.example.registration.service.domains.User;
import java.time.LocalDate;

public class UserTemplateLoader implements TemplateLoader {

  public static final String VALID_USER = "VALID_USER";
  public static final String BLACK_LIST_USER = "BLACK_LIST_USER";

  @Override
  public void load() {

    Fixture.of(User.class)
        .addTemplate(
            VALID_USER,
            new Rule() {
              {
                add("username", "claudionazareth");
                add("password", "1qazxsW2");
                add("dateOfBirth", LocalDate.parse("1983-05-24"));
                add("socialSecurityNumber", "987-65-4329");
              }
            });

    Fixture.of(User.class)
        .addTemplate(
            BLACK_LIST_USER,
            new Rule() {
              {
                add("username", "black_list_user");
                add("password", "1qazxsW2");
                add("dateOfBirth", LocalDate.parse("1990-10-01"));
                add("socialSecurityNumber", "981-65-4329");
              }
            });
  }
}
