package com.example.registration.service.gateways.mongo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;

import org.junit.Test;

public class UserGatewayImplTest {

  @Test
  public void should_throw_IllegalArgumentException_when_UserRepository_is_null() {

    Throwable thrown = catchThrowable(() -> new UserGatewayImpl(null));

    assertThat(thrown).isExactlyInstanceOf(IllegalArgumentException.class);
    assertThat(thrown).hasMessageContaining("UserRepository is required");
  }
}
