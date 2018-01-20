package com.example.registration.service.fixtures;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.example.registration.service.controllers.jsons.UserRequest;
import java.time.LocalDate;

public class UserRequestTemplateLoader implements TemplateLoader {

  public static final String VALID_USER_REQUEST = "VALID_USER_REQUEST";
  public static final String INVALID_PASSWORD = "INVALID_PASSWORD";

  @Override
  public void load() {

    Fixture.of(UserRequest.class)
        .addTemplate(
            VALID_USER_REQUEST,
            new Rule() {
              {
                add("username", "claudionazareth");
                add("password", "1qazxsW2");
                add("dateOfBirth", LocalDate.parse("1983-05-24"));
                add("socialSecurityNumber", "987-65-4329");
              }
            });

    Fixture.of(UserRequest.class)
        .addTemplate(
            INVALID_PASSWORD,
            new Rule() {
              {
                add("username", "claudionazareth");
                add("password", "123456");
                add("dateOfBirth", LocalDate.parse("1990-10-02"));
                add(
                    "socialSecurityNumber",
                    random("187-65-4329", "287-65-4329", "387-65-4329", "487-65-4329"));
              }
            });
  }
}
