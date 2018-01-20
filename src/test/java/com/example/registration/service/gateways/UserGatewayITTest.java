package com.example.registration.service.gateways;

import static com.example.registration.service.fixtures.UserTemplateLoader.VALID_USER;
import static org.assertj.core.api.Assertions.assertThat;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.example.registration.service.domains.User;
import com.example.registration.service.gateways.mongo.repository.UserRepository;
import java.time.LocalDate;
import java.util.Optional;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserGatewayITTest {

  @Autowired private UserGateway userGateway;

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
  public void should_register_a_valid_user() {
    final User user = userGateway.register(Fixture.from(User.class).gimme(VALID_USER));

    assertThat(user.getId()).as("Id should not be null or blank").isNotBlank().isNotNull();

    assertThat(user)
        .isNotNull()
        .extracting("username", "dateOfBirth", "socialSecurityNumber")
        .contains("claudionazareth", LocalDate.of(1983, 05, 24), "987-65-4329");
  }

  @Test
  public void should_return_user_when_its_in_data_base() {
    userGateway.register(Fixture.from(User.class).gimme(VALID_USER));
    final User user = userGateway.findByUsername("claudionazareth").get();

    assertThat(user)
        .as("User must not be null")
        .isNotNull()
        .extracting("username", "dateOfBirth", "socialSecurityNumber")
        .contains("claudionazareth", LocalDate.of(1983, 05, 24), "987-65-4329");
  }

  @Test
  public void should_return_empty_when_user_not_in_the_data_base() {

    final Optional<User> userOptional = userGateway.findByUsername("claudionazareth");

    assertThat(userOptional.isPresent()).as("User must not be present").isFalse();
  }
}
