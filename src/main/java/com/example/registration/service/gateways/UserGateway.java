package com.example.registration.service.gateways;

import com.example.registration.service.domains.User;
import java.util.Optional;

public interface UserGateway {
  User register(User user);

  Optional<User> findByUsername(String username);
}
