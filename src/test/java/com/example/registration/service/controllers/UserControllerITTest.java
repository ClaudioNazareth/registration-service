package com.example.registration.service.controllers;

import static com.example.registration.service.fixtures.UserRequestTemplateLoader.VALID_USER_REQUEST;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.example.registration.service.controllers.jsons.UserRequest;
import com.example.registration.service.gateways.mongo.repository.UserRepository;
import javax.servlet.http.HttpServletRequest;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerITTest {

  @Autowired private UserController userController;

  @Autowired private UserRepository userRepository;

  private MockMvc mockMvc;

  @Autowired private WebApplicationContext wac;

  @BeforeClass
  public static void beforeClass() {
    FixtureFactoryLoader.loadTemplates("com.example.registration.service.fixtures");
  }

  @Before
  public void setUp() {
    userRepository.deleteAll();
    HttpServletRequest mockRequest = new MockHttpServletRequest();
    ServletRequestAttributes servletRequestAttributes = new ServletRequestAttributes(mockRequest);
    RequestContextHolder.setRequestAttributes(servletRequestAttributes);
    userRepository.deleteAll();
    this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
  }

  @Test
  public void register_user() {
    ResponseEntity<?> responseEntity =
        userController.register(Fixture.from(UserRequest.class).gimme(VALID_USER_REQUEST));

    assertThat(responseEntity).as("User must be successfully created").isNotNull();

    assertThat(responseEntity.getStatusCode())
        .as("Status code must be created")
        .isEqualTo(HttpStatus.CREATED);

    assertThat(responseEntity.getHeaders().get("location"))
        .as("Should return the URL of the created user")
        .isNotNull();
  }

  @Test
  public void should_throw_IllegalArgumentException_when_RegisterUser_is_null() {

    Throwable thrown = catchThrowable(() -> new UserController(null));

    assertThat(thrown).isExactlyInstanceOf(IllegalArgumentException.class);
    assertThat(thrown).hasMessageContaining("RegisterUser is required");
  }

  @Test
  public void should_reject_invalid_username() throws Exception {
    this.mockMvc
        .perform(
            post("/api/v1/users/register")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(getInvalidUsername()))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void should_reject_invalid_password() throws Exception {
    this.mockMvc
        .perform(
            post("/api/v1/users/register")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(getInvalidPassword()))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void should_return_forbidden_when_user_in_black_list() throws Exception {
    this.mockMvc
        .perform(
            post("/api/v1/users/register")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(getBlackListUser()))
        .andExpect(status().isForbidden());
  }

  @Test
  public void should_return_Conflict_when_user_in_black_list() throws Exception {
    this.mockMvc
        .perform(
            post("/api/v1/users/register")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(getUserAlreadyRegistered()))
        .andExpect(status().isCreated());

    this.mockMvc
        .perform(
            post("/api/v1/users/register")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(getUserAlreadyRegistered()))
        .andExpect(status().isConflict());
  }

  private String getInvalidUsername() {
    return "{\n"
        + "  \"dateOfBirth\": \"1983-05-24\",\n"
        + "  \"password\": \"M1re\",\n"
        + "  \"socialSecurityNumber\": \"987-62-4329\",\n"
        + "  \"username\": \"claudio \"\n"
        + "}";
  }

  private String getInvalidPassword() {
    return "{\n"
        + "  \"dateOfBirth\": \"1983-05-24\",\n"
        + "  \"password\": \"12345\",\n"
        + "  \"socialSecurityNumber\": \"987-62-4329\",\n"
        + "  \"username\": \"claudio\"\n"
        + "}";
  }

  private String getBlackListUser() {
    return "{\n"
        + "  \"dateOfBirth\": \"1990-10-01\",\n"
        + "  \"password\": \"M1re\",\n"
        + "  \"socialSecurityNumber\": \"981-65-4329\",\n"
        + "  \"username\": \"claudio\"\n"
        + "}";
  }

  private String getUserAlreadyRegistered() {
    return "{\n"
        + "  \"dateOfBirth\": \"1983-05-24\",\n"
        + "  \"password\": \"M1re\",\n"
        + "  \"socialSecurityNumber\": \"987-65-4329\",\n"
        + "  \"username\": \"claudio\"\n"
        + "}";
  }
}
