package com.example.registration.service.controllers;

import static org.springframework.http.ResponseEntity.created;

import com.example.registration.service.controllers.jsons.UserRequest;
import com.example.registration.service.domains.User;
import com.example.registration.service.usecases.RegisterUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.net.URI;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/v1/users")
@Api(
  value = "Users",
  consumes = MediaType.APPLICATION_JSON_VALUE,
  produces = MediaType.APPLICATION_JSON_VALUE,
  tags = {"Endpoint to register a user"},
  description = "Rest API for user registration",
  basePath = "/api/v1/users"
)
public class UserController {

  private static final Logger logger = LoggerFactory.getLogger(UserController.class);

  private RegisterUser registerUser;

  @Autowired
  public UserController(RegisterUser registerUser) {
    Assert.notNull(registerUser, "RegisterUser is required");
    this.registerUser = registerUser;
  }

  @PostMapping(path = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation(
    value = "Creates a new user based on parameter data",
    notes = "Create a new user and return the path link in the header",
    response = ResponseEntity.class
  )
  @ApiResponses(
    value = {
      @ApiResponse(code = 201, message = "Created"),
      @ApiResponse(code = 400, message = "Bad Request"),
      @ApiResponse(code = 403, message = "Forbidden"),
      @ApiResponse(code = 409, message = "Conflict"),
      @ApiResponse(code = 500, message = "Internal Server Error")
    }
  )
  public ResponseEntity<?> register(@Valid @RequestBody UserRequest userRequest) {

    logger.debug("User received to register:  {} ", userRequest);

    final User user = registerUser.register(userRequest.toDomain());

    URI location =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(user.getUsername())
            .toUri();

    return created(location).build();
  }
}
