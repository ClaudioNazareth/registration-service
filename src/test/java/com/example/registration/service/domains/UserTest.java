package com.example.registration.service.domains;

import static com.example.registration.service.fixtures.UserTemplateLoader.BLACK_LIST_USER;
import static com.example.registration.service.fixtures.UserTemplateLoader.VALID_USER;
import static org.assertj.core.api.Assertions.assertThat;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import org.junit.BeforeClass;
import org.junit.Test;

public class UserTest {

  @BeforeClass
  public static void beforeClass() {
    FixtureFactoryLoader.loadTemplates("com.example.registration.service.fixtures");
  }

  @Test
  public void testEquals() {
    User user1 = Fixture.from(User.class).gimme(VALID_USER);
    User user2 = Fixture.from(User.class).gimme(VALID_USER);
    assertThat(user1.equals(user2)).as("Users must be equal").isTrue();
    assertThat(user1.equals(user1)).as("Users must be equal").isTrue();

    user2 = Fixture.from(User.class).gimme(BLACK_LIST_USER);

    assertThat(user1.equals(user2)).as("Users must not be equal").isFalse();

    assertThat(user1.equals(null)).as("Users must not be equal").isFalse();
  }

  @Test
  public void testHashCode() {

    User user1 = Fixture.from(User.class).gimme(VALID_USER);
    User user2 = Fixture.from(User.class).gimme(VALID_USER);
    assertThat(user1.hashCode() == user2.hashCode()).as("hashCode must be equal").isTrue();

    user2 = Fixture.from(User.class).gimme(BLACK_LIST_USER);

    assertThat(user1.hashCode() == user2.hashCode()).as("hashCode must not be equals").isFalse();
  }
}
