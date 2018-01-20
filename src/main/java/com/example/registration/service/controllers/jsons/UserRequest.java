package com.example.registration.service.controllers.jsons;

import static com.example.registration.service.utils.RegexUtils.PASSWORD_REGEX;
import static com.example.registration.service.utils.RegexUtils.USER_NAME_REGEX;

import com.example.registration.service.domains.User;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDate;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@ApiModel(
  value = "UserRequest",
  description =
      "Represents all user data that must be received by The rest api Rest to register an user"
)
public class UserRequest {

  @NotNull(message = "username must not be null")
  @Pattern(regexp = USER_NAME_REGEX, message = "The username must be alphanumerical, no spaces")
  @ApiModelProperty(value = "Username", dataType = "string", required = true)
  private String username;

  @NotNull(message = "password must not be null")
  @Pattern(
    regexp = PASSWORD_REGEX,
    message =
        "The password must be at least four characters, at least one upper case character, at least one number"
  )
  @ApiModelProperty(value = "Password", dataType = "string", required = true)
  private String password;

  @NotNull(message = "dateOfBirth must not be null")
  @JsonDeserialize(using = LocalDateDeserializer.class)
  @ApiModelProperty(
    value = "User's date of birth",
    dataType = "Date",
    required = true,
    example = "1983-05-24"
  )
  private LocalDate dateOfBirth;

  @NotNull(message = "socialSecurityNumber must not be null")
  @ApiModelProperty(
    value = "Social Security Number",
    dataType = "Date",
    required = true,
    example = "987-65-4329"
  )
  private String socialSecurityNumber;

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public LocalDate getDateOfBirth() {
    return dateOfBirth;
  }

  public String getSocialSecurityNumber() {
    return socialSecurityNumber;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("UserRequest {");
    sb.append("username='").append(username).append('\'');
    sb.append(", password='").append(password).append('\'');
    sb.append(", dateOfBirth=").append(dateOfBirth);
    sb.append(", socialSecurityNumber='").append(socialSecurityNumber).append('\'');
    sb.append('}');
    return sb.toString();
  }

  public User toDomain() {
    return new User(username, password, dateOfBirth, socialSecurityNumber);
  }
}
