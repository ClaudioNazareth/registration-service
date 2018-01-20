package com.example.registration.service.domains;

import static com.example.registration.service.utils.RegexUtils.USER_NAME_REGEX;

import com.example.registration.service.utils.PasswordUtils;
import java.time.LocalDate;
import java.util.Objects;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "users")
public class User {

  @Id private String id;

  @NotNull
  @Field(value = "username")
  @Indexed(unique = true)
  @Pattern(regexp = USER_NAME_REGEX, message = "${username.validation.message}")
  private String username;

  @NotNull
  @Field(value = "password")
  private String password;

  @Indexed
  @NotNull
  @Field(value = "dateOfBirth")
  private LocalDate dateOfBirth;

  @NotNull
  @Indexed(unique = true)
  private String socialSecurityNumber;

  public User(
      String username, String password, LocalDate dateOfBirth, String socialSecurityNumber) {
    this.username = username;
    this.password = PasswordUtils.generateBCrypt(password);
    this.dateOfBirth = dateOfBirth;
    this.socialSecurityNumber = socialSecurityNumber;
  }

  public String getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }

  public LocalDate getDateOfBirth() {
    return dateOfBirth;
  }

  public String getSocialSecurityNumber() {
    return socialSecurityNumber;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    User user = (User) o;
    return Objects.equals(username, user.username)
        && Objects.equals(dateOfBirth, user.dateOfBirth)
        && Objects.equals(socialSecurityNumber, user.socialSecurityNumber);
  }

  @Override
  public int hashCode() {
    return Objects.hash(username, dateOfBirth, socialSecurityNumber);
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("User {");
    sb.append(" username='").append(username).append('\'');
    sb.append(", dateOfBirth=").append(dateOfBirth);
    sb.append(", socialSecurityNumber='").append(socialSecurityNumber).append('\'');
    sb.append('}');
    return sb.toString();
  }
}
