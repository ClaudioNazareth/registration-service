package com.example.registration.service.fixtures;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.example.registration.service.domains.UserBlackList;
import java.time.LocalDate;

public class UserBlackListTemplateLoader implements TemplateLoader {

  public static final String BLACK_LIST_USER_1 = "BLACK_LIST_USER_1";
  public static final String BLACK_LIST_USER_2 = "BLACK_LIST_USER_2";

  @Override
  public void load() {
    Fixture.of(UserBlackList.class)
        .addTemplate(
            BLACK_LIST_USER_1,
            new Rule() {
              {
                add("dateOfBirth", LocalDate.parse("1990-10-01"));
                add("socialSecurityNumber", "981-65-4329");
              }
            });

    Fixture.of(UserBlackList.class)
        .addTemplate(
            BLACK_LIST_USER_2,
            new Rule() {
              {
                add("dateOfBirth", LocalDate.parse("1983-05-24"));
                add("socialSecurityNumber", "982-65-4329");
              }
            });
  }
}
