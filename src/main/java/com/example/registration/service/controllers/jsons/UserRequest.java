package com.example.registration.service.controllers.jsons;

import static com.example.registration.service.utils.ValidationUtils.PASSWORD_REGEX;
import static com.example.registration.service.utils.ValidationUtils.PASSWORD_VALIDATION_MESSAGE;
import static com.example.registration.service.utils.ValidationUtils.USER_NAME_REGEX;
import static com.example.registration.service.utils.ValidationUtils.USER_NAME_VALIDATION_MESSAGE;

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
  description = "Represents all user data that must be received by the rest api to register an user"
)
public class UserRequest {

  @NotNull
  @Pattern(regexp = USER_NAME_REGEX, message = USER_NAME_VALIDATION_MESSAGE)
  @ApiModelProperty(
    value = "Username (alphanumerical, no spaces)",
    dataType = "string",
    required = true,
    notes = "alphanumerical, no spaces",
    example = "gamesys"
  )
  private String username;

  @NotNull
  @Pattern(regexp = PASSWORD_REGEX, message = PASSWORD_VALIDATION_MESSAGE)
  @ApiModelProperty(
    value =
        "Password (at least four characters, at least one upper case character, at least one number)",
    dataType = "string",
    required = true,
    notes = "at least four characters, at least one upper case character, at least one number",
    example = "Gamesys1"
  )
  private String password;

  @NotNull
  @JsonDeserialize(using = LocalDateDeserializer.class)
  @ApiModelProperty(
    value = "User's date of birth (ISO 8601)",
    dataType = "Date",
    required = true,
    example = "1983-05-24",
    notes = "ISO 8601"
  )
  private LocalDate dateOfBirth;

  @NotNull
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
