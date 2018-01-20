package com.example.registration.service.usecases;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ExclusionServiceIT {

  @Autowired private ExclusionService exclusionService;

  @Test
  public void should_return_true_when_user_not_in_black_list() {
    final boolean isUserValid = exclusionService.validate("1983-05-24", "987-65-4329");
    assertThat(isUserValid).as("The users should be valid").isTrue();
  }

  @Test
  public void should_return_false_when_user_is_in_black_list() {
    final boolean isUserValid = exclusionService.validate("1990-10-01", "981-65-4329");
    assertThat(isUserValid).as("The users should not be valid").isFalse();
  }
}
