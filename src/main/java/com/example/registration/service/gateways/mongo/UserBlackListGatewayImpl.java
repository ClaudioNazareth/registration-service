package com.example.registration.service.gateways.mongo;

import com.example.registration.service.domains.UserBlackList;
import com.example.registration.service.gateways.UserBlackListGateway;
import com.example.registration.service.gateways.mongo.repository.UserBlackListRepository;
import java.time.LocalDate;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * Gateway to register and find for UserBlackList on MongoDb
 *
 * @author Claudio Nazareth
 */
@Component
public class UserBlackListGatewayImpl implements UserBlackListGateway {

  private static final Logger logger = LoggerFactory.getLogger(UserBlackListGateway.class);

  private UserBlackListRepository userBlackListRepository;

  public UserBlackListGatewayImpl(UserBlackListRepository userBlackListRepository) {
    Assert.notNull(userBlackListRepository, "UserBlackListRepository is required");
    this.userBlackListRepository = userBlackListRepository;
  }

  @Override
  public Optional<UserBlackList> findByDateOfBirthAndSsn(String dob, String ssn) {
    logger.debug("Finding UserBlackList by dateOfBirth: {} and ssn: {}", dob, ssn);

    return userBlackListRepository.findByDateOfBirthAndSocialSecurityNumber(
        LocalDate.parse(dob), ssn);
  }
}
