package com.example.registration.service.controllers;

import com.example.registration.service.controllers.jsons.ErrorResponse;
import com.example.registration.service.exceptions.UserAlreadyExistsException;
import com.example.registration.service.exceptions.UserInBlackListException;
import com.mongodb.DuplicateKeyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * Center all application exceptions to give correct http return
 *
 * @author Claudio Nazareth
 */
@ControllerAdvice
public class CustomExceptionHandler {

  private static final Logger logger = LoggerFactory.getLogger(CustomExceptionHandler.class);

  /**
   * Handle UserInBlackListException and return http 403 - Forbidden
   *
   * @param e | UserInBlackListException
   * @return ErrorResponse
   */
  @ResponseBody
  @ResponseStatus(HttpStatus.FORBIDDEN)
  @ExceptionHandler(UserInBlackListException.class)
  public ErrorResponse handleUserInBlackListException(Exception e) {
    logger.error("{}", e);
    return new ErrorResponse(
        ServletUriComponentsBuilder.fromCurrentRequest().path("").toUriString(), e);
  }

  /**
   * Handle UserAlreadyExistsException and return http 409 - Conflict
   *
   * @param e | UserAlreadyExistsException
   * @return ErrorResponse
   */
  @ResponseBody
  @ResponseStatus(HttpStatus.CONFLICT)
  @ExceptionHandler(UserAlreadyExistsException.class)
  public ErrorResponse handleUserAlreadyExists(Exception e) {
    logger.error("{}", e);
    return new ErrorResponse(
        ServletUriComponentsBuilder.fromCurrentRequest().path("").toUriString(), e);
  }

  /**
   * Handle DuplicateKeyException and return http 409 - Conflict
   *
   * @param e | DuplicateKeyException
   * @return ErrorResponse
   */
  @ResponseBody
  @ResponseStatus(HttpStatus.CONFLICT)
  @ExceptionHandler(DuplicateKeyException.class)
  public ErrorResponse handleDuplicateKeyException(Exception e) {
    logger.error("{}", e);
    return new ErrorResponse(
        ServletUriComponentsBuilder.fromCurrentRequest().path("").toUriString(), e);
  }

  /**
   * Handle HttpMessageNotReadableException and return http 400 - Bad request
   *
   * @param e | HttpMessageNotReadableException
   * @return ErrorResponse
   */
  @ResponseBody
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ErrorResponse handleHttpMessageNotReadableException(Exception e) {
    logger.error("{}", e);
    return new ErrorResponse(
        ServletUriComponentsBuilder.fromCurrentRequest().path("").toUriString(), e);
  }
}
