package com.example.registration.service.domains;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import java.time.LocalDate;
import java.util.Objects;
import javax.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "usersBlackList")
public class UserBlackList {

  @Id private String id;

  @Indexed
  @NotNull
  @Field(value = "dateOfBirth")
  @JsonDeserialize(using = LocalDateDeserializer.class)
  private LocalDate dateOfBirth;

  @NotNull
  @Indexed(unique = true)
  private String socialSecurityNumber;

  public LocalDate getDateOfBirth() {
    return dateOfBirth;
  }

  public String getSocialSecurityNumber() {
    return socialSecurityNumber;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("UserBlackList{");
    sb.append("id='").append(id).append('\'');
    sb.append(", dateOfBirth=").append(dateOfBirth);
    sb.append(", socialSecurityNumber='").append(socialSecurityNumber).append('\'');
    sb.append('}');
    return sb.toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserBlackList that = (UserBlackList) o;
    return Objects.equals(id, that.id)
        && Objects.equals(dateOfBirth, that.dateOfBirth)
        && Objects.equals(socialSecurityNumber, that.socialSecurityNumber);
  }

  @Override
  public int hashCode() {
    return Objects.hash(dateOfBirth, socialSecurityNumber);
  }
}
