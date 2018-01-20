package com.example.registration.service.gateways;

import com.example.registration.service.domains.UserBlackList;
import java.util.Optional;

public interface UserBlackListGateway {
  Optional<UserBlackList> findByDateOfBirthAndSsn(String dob, String ssn);
}
