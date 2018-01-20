package com.example.registration.service.controllers;

import static com.example.registration.service.fixtures.UserTemplateLoader.VALID_USER;
import static org.assertj.core.api.Assertions.assertThat;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.example.registration.service.controllers.jsons.ErrorResponse;
import com.example.registration.service.domains.User;
import com.example.registration.service.exceptions.UserAlreadyExistsException;
import com.example.registration.service.exceptions.UserInBlackListException;
import javax.servlet.http.HttpServletRequest;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class CustomExceptionHandlerTest {

  private CustomExceptionHandler customExceptionHandler = new CustomExceptionHandler();

  @BeforeClass
  public static void beforeClass() {
    FixtureFactoryLoader.loadTemplates("com.example.registration.service.fixtures");
  }

  @Before
  public void setUp() throws Exception {
    HttpServletRequest mockRequest = new MockHttpServletRequest();
    ServletRequestAttributes servletRequestAttributes = new ServletRequestAttributes(mockRequest);
    RequestContextHolder.setRequestAttributes(servletRequestAttributes);
  }

  @Test
  public void handleUserInBlackListException() {
    ErrorResponse errorResponse =
        customExceptionHandler.handleUserInBlackListException(
            new UserInBlackListException(Fixture.from(User.class).gimme(VALID_USER)));
    assertThat(errorResponse.getEx()).contains("User in black list can not be registered");
  }

  @Test
  public void handleUserAlreadyExists() {
    ErrorResponse errorResponse =
        customExceptionHandler.handleUserAlreadyExists(
            new UserAlreadyExistsException(Fixture.from(User.class).gimme(VALID_USER)));
    assertThat(errorResponse.getEx())
        .contains("User with username: claudionazareth already registered");
  }
}
