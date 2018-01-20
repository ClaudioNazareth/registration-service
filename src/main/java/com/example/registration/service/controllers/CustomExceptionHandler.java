package com.example.registration.service.controllers;

import com.example.registration.service.controllers.jsons.ErrorResponse;
import com.example.registration.service.exceptions.UserAlreadyExistsException;
import com.example.registration.service.exceptions.UserInBlackListException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@ControllerAdvice
public class CustomExceptionHandler {

  private static final Logger logger = LoggerFactory.getLogger(CustomExceptionHandler.class);

  @ResponseBody
  @ResponseStatus(HttpStatus.FORBIDDEN)
  @ExceptionHandler(UserInBlackListException.class)
  public ErrorResponse handleUserInBlackListException(Exception e) {
    logger.error("{}", e);
    return new ErrorResponse(
        ServletUriComponentsBuilder.fromCurrentRequest().path("").toUriString(), e);
  }

  @ResponseBody
  @ResponseStatus(HttpStatus.CONFLICT)
  @ExceptionHandler(UserAlreadyExistsException.class)
  public ErrorResponse handleUserAlreadyExists(Exception e) {
    logger.error("{}", e);
    return new ErrorResponse(
        ServletUriComponentsBuilder.fromCurrentRequest().path("").toUriString(), e);
  }
}
