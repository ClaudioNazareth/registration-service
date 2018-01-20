package com.example.registration.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

import com.example.registration.service.gateways.mongo.repository.UserBlackListRepository;
import java.time.LocalDate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RegistrationServiceApplicationStarterIT {

  @Autowired private UserBlackListRepository userBlackListRepository;

  @Test
  public void when_application_starts_should_load_userblacklist_to_mongo() {
    assertThat(userBlackListRepository.findAll())
        .as(
            "When application starts the data from the data.json file should be uploaded to MongoDB in memory")
        .isNotNull()
        .isNotEmpty()
        .as("There must be four(4) records in MongoDB")
        .hasSize(4)
        .as("All fields must be filled and the data must be correct")
        .extracting("dateOfBirth", "socialSecurityNumber")
        .contains(
            tuple(LocalDate.of(1990, 10, 01), "981-65-4329"),
            tuple(LocalDate.of(1983, 05, 24), "982-65-4329"),
            tuple(LocalDate.of(1997, 12, 31), "983-65-4329"),
            tuple(LocalDate.of(1994, 07, 15), "984-65-4329"));
  }
}
