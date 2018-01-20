package com.example.registration.service.usecases;

import static com.example.registration.service.fixtures.UserTemplateLoader.BLACK_LIST_USER;
import static com.example.registration.service.fixtures.UserTemplateLoader.VALID_USER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.example.registration.service.domains.User;
import com.example.registration.service.exceptions.UserAlreadyExistsException;
import com.example.registration.service.exceptions.UserInBlackListException;
import com.example.registration.service.gateways.mongo.repository.UserRepository;
import java.time.LocalDate;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RegisterUserIT {

  @Autowired private RegisterUser registerUser;

  @Autowired private UserRepository userRepository;

  @BeforeClass
  public static void beforeClass() {
    FixtureFactoryLoader.loadTemplates("com.example.registration.service.fixtures");
  }

  @Before
  public void setUp() {
    userRepository.deleteAll();
  }

  @Test
  public void should_register_user_when_it_is_not_in_the_black_list_or_registered() {
    final User user = registerUser.register(Fixture.from(User.class).gimme(VALID_USER));
    assertThat(user.getId()).as("Id should not be null or blank").isNotBlank().isNotNull();

    assertThat(user)
        .isNotNull()
        .extracting("username", "dateOfBirth", "socialSecurityNumber")
        .contains("claudionazareth", LocalDate.of(1983, 05, 24), "987-65-4329");
  }

  @Test
  public void should_throw_UserInBlackListException_when_it_in_the_black_list() {
    Throwable thrown =
        catchThrowable(
            () -> registerUser.register(Fixture.from(User.class).gimme(BLACK_LIST_USER)));

    assertThat(thrown).isExactlyInstanceOf(UserInBlackListException.class);
    assertThat(thrown).hasMessageContaining("User in black list can not be registered");
  }

  @Test
  public void should_throw_UserAlreadyExistsException_when_it_already_registered() {
    registerUser.register(Fixture.from(User.class).gimme(VALID_USER));

    Throwable thrown =
        catchThrowable(() -> registerUser.register(Fixture.from(User.class).gimme(VALID_USER)));

    assertThat(thrown).isExactlyInstanceOf(UserAlreadyExistsException.class);
    assertThat(thrown)
        .hasMessageContaining("User with username: claudionazareth already registered");
  }
}
