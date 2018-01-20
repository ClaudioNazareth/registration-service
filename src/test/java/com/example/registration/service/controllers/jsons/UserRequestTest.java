package com.example.registration.service.controllers.jsons;

import static com.example.registration.service.fixtures.UserRequestTemplateLoader.VALID_USER_REQUEST;
import static org.assertj.core.api.Assertions.assertThat;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import java.time.LocalDate;
import org.junit.BeforeClass;
import org.junit.Test;

public class UserRequestTest {

  @BeforeClass
  public static void beforeClass() {
    FixtureFactoryLoader.loadTemplates("com.example.registration.service.fixtures");
  }

  @Test
  public void testUserRequest() {
    UserRequest userRequest = Fixture.from(UserRequest.class).gimme(VALID_USER_REQUEST);

    assertThat(userRequest.getUsername()).isEqualTo("claudionazareth");
    assertThat(userRequest.getDateOfBirth()).isEqualTo(LocalDate.of(1983, 05, 24));
    assertThat(userRequest.getSocialSecurityNumber()).isEqualTo("987-65-4329");

    assertThat(userRequest.toString())
        .isEqualTo(
            "UserRequest {username='claudionazareth', password='1qazxsW2', "
                + "dateOfBirth=1983-05-24, socialSecurityNumber='987-65-4329'}");
  }
}
