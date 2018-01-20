package com.example.registration.service.domains;

import static com.example.registration.service.fixtures.UserBlackListTemplateLoader.BLACK_LIST_USER_1;
import static com.example.registration.service.fixtures.UserBlackListTemplateLoader.BLACK_LIST_USER_2;
import static org.assertj.core.api.Assertions.assertThat;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import org.junit.BeforeClass;
import org.junit.Test;

public class UserBlackListTest {

  @BeforeClass
  public static void beforeClass() {
    FixtureFactoryLoader.loadTemplates("com.example.registration.service.fixtures");
  }

  @Test
  public void testEquals() {
    UserBlackList user1 = Fixture.from(UserBlackList.class).gimme(BLACK_LIST_USER_1);
    UserBlackList user2 = Fixture.from(UserBlackList.class).gimme(BLACK_LIST_USER_1);
    assertThat(user1.equals(user2)).as("Users must be equal").isTrue();
    assertThat(user1.equals(user1)).as("Users must be equal").isTrue();

    user2 = Fixture.from(UserBlackList.class).gimme(BLACK_LIST_USER_2);

    assertThat(user1.equals(user2)).as("Users must not be equal").isFalse();

    assertThat(user1.equals(null)).as("Users must not be equal").isFalse();
  }

  @Test
  public void testHashCode() {

    UserBlackList user1 = Fixture.from(UserBlackList.class).gimme(BLACK_LIST_USER_1);
    UserBlackList user2 = Fixture.from(UserBlackList.class).gimme(BLACK_LIST_USER_1);
    assertThat(user1.hashCode() == user2.hashCode()).as("hashCode must be equal").isTrue();

    user2 = Fixture.from(UserBlackList.class).gimme(BLACK_LIST_USER_2);

    assertThat(user1.hashCode() == user2.hashCode()).as("hashCode must not be equals").isFalse();
  }
}
