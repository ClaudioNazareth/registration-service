package com.example.registration.service.usecases;

import com.example.registration.service.domains.UserBlackList;
import com.example.registration.service.gateways.UserBlackListGateway;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * Use case that implements ExclusionService to offer validation of a user against a 'blacklist'.
 * Blacklisted users fail the validation.
 *
 * @author Claudio Nazareth
 */
@Service
public class VerifyBlackList implements ExclusionService {

  private UserBlackListGateway userBlackListGateway;

  public VerifyBlackList(UserBlackListGateway userBlackListGateway) {
    Assert.notNull(userBlackListGateway, "UserBlackListGateway is required");
    this.userBlackListGateway = userBlackListGateway;
  }

  @Override
  public boolean validate(String dob, String ssn) {

    final Optional<UserBlackList> userBlackListOptional =
        userBlackListGateway.findByDateOfBirthAndSsn(dob, ssn);

    return userBlackListOptional.isPresent() ? false : true;
  }
}
