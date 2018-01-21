package com.example.registration.service.gateways;

import com.example.registration.service.domains.User;
import java.util.Optional;

/**
 * Gateway to register and find for users regardless of location, no matter whether it is a MongoDb
 * or Oracle database, text file or in the cloud.
 *
 * @author Claudio Nazareth
 */
public interface UserGateway {

  /**
   * Register the user
   *
   * @param user to be registered
   * @return a registered user with filled id
   */
  User register(User user);

  /**
   * Find an user by username
   *
   * @param username
   * @return Optional<User>
   */
  Optional<User> findByUsername(String username);
}
