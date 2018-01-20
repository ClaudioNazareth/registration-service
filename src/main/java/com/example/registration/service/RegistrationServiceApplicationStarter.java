package com.example.registration.service;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.repository.init.AbstractRepositoryPopulatorFactoryBean;
import org.springframework.data.repository.init.Jackson2RepositoryPopulatorFactoryBean;

@SpringBootApplication
public class RegistrationServiceApplicationStarter {

  public static void main(String[] args) {
    SpringApplication.run(RegistrationServiceApplicationStarter.class, args);
  }

  // Responsible for reading the resources file (data.json) and uploading to MongoDB
  @Bean
  public AbstractRepositoryPopulatorFactoryBean repositoryPopulator() {

    ObjectMapper mapper = new ObjectMapper();
    mapper.configure(FAIL_ON_UNKNOWN_PROPERTIES, false);

    Jackson2RepositoryPopulatorFactoryBean factoryBean =
        new Jackson2RepositoryPopulatorFactoryBean();
    factoryBean.setResources(new Resource[] {new ClassPathResource("data.json")});
    factoryBean.setMapper(mapper);

    return factoryBean;
  }
}
