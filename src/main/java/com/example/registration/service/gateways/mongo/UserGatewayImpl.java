package com.example.registration.service.gateways.mongo;

import com.example.registration.service.domains.User;
import com.example.registration.service.gateways.UserGateway;
import com.example.registration.service.gateways.mongo.repository.UserRepository;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class UserGatewayImpl implements UserGateway {

  private static final Logger logger = LoggerFactory.getLogger(UserGateway.class);

  private UserRepository userRepository;

  @Autowired
  public UserGatewayImpl(UserRepository userRepository) {
    Assert.notNull(userRepository, "UserRepository is required");
    this.userRepository = userRepository;
  }

  @Override
  public User register(User user) {
    logger.debug("Saving user : {}", user);
    return userRepository.save(user);
  }

  @Override
  public Optional<User> findByUsername(String username) {
    logger.debug("Finding user by username : {}", username);
    return userRepository.findByUsername(username);
  }
}
