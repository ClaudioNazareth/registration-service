package com.example.registration.service.gateways.mongo.repository;

import com.example.registration.service.domains.UserBlackList;
import java.time.LocalDate;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserBlackListRepository extends MongoRepository<UserBlackList, String> {

  Optional<UserBlackList> findByDateOfBirthAndSocialSecurityNumber(
      LocalDate dateOfBirth, String socialSecurityNumber);
}
