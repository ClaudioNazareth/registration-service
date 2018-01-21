package com.example.registration.service.gateways;

import com.example.registration.service.domains.UserBlackList;
import java.util.Optional;

/**
 * Gateway to register and find for userBlackList regardless of location, no matter whether it is a
 * MongoDb or Oracle database, text file or in the cloud.
 *
 * @author Claudio Nazareth
 */
public interface UserBlackListGateway {

  /**
   * Find a UserBlackList by date of birth and social security number
   *
   * @param dob the user's date of birth in ISO 8601 format
   * @param ssn the user's social security number (United States)
   * @return Optional<UserBlackList>
   */
  Optional<UserBlackList> findByDateOfBirthAndSsn(String dob, String ssn);
}
