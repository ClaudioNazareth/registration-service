package com.example.registration.service.gateways;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.registration.service.domains.UserBlackList;
import java.time.LocalDate;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserBlackListGatewayIT {

  @Autowired private UserBlackListGateway userBlackListGateway;

  @Test
  public void find_by_date_of_birth_and_ssn() {

    final UserBlackList userBlackList =
        userBlackListGateway.findByDateOfBirthAndSsn("1983-05-24", "982-65-4329").get();

    assertThat(userBlackList)
        .as("UserBlackList should not be null")
        .isNotNull()
        .extracting("dateOfBirth", "socialSecurityNumber")
        .containsExactly(LocalDate.of(1983, 05, 24), "982-65-4329");
  }

  @Test
  public void find_by_date_of_birth_and_ssn_should_return_empty_when_not_find() {

    final Optional<UserBlackList> userBlackListOptional =
        userBlackListGateway.findByDateOfBirthAndSsn("1984-05-24", "100-65-4329");

    assertThat(userBlackListOptional.isPresent()).as("UserBlackList must not be present").isFalse();
  }
}
