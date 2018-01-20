package com.example.registration.service.usecases;

import com.example.registration.service.domains.User;
import com.example.registration.service.exceptions.UserAlreadyExistsException;
import com.example.registration.service.exceptions.UserInBlackListException;
import com.example.registration.service.gateways.UserGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class RegisterUser {

  private UserGateway userGateway;

  private ExclusionService exclusionService;

  @Autowired
  public RegisterUser(UserGateway userGateway, ExclusionService exclusionService) {
    Assert.notNull(userGateway, "UserGateway is required");
    Assert.notNull(exclusionService, "ExclusionService is required");
    this.userGateway = userGateway;
    this.exclusionService = exclusionService;
  }

  public User register(User user) {

    if (isUserNotInTheBlackList(user)) {

      if (isUserNotRegistered(user)) {
        return userGateway.register(user);
      } else {
        throw new UserAlreadyExistsException(user);
      }

    } else {
      throw new UserInBlackListException(user);
    }
  }

  private boolean isUserNotInTheBlackList(User user) {
    return exclusionService.validate(
        user.getDateOfBirth().toString(), user.getSocialSecurityNumber());
  }

  private boolean isUserNotRegistered(User user) {
    return !userGateway.findByUsername(user.getUsername()).isPresent();
  }
}
