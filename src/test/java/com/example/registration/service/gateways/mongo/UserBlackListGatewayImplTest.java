package com.example.registration.service.gateways.mongo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;

import org.junit.Test;

public class UserBlackListGatewayImplTest {

  @Test
  public void should_throw_IllegalArgumentException_when_UserBlackListRepository_is_null() {

    Throwable thrown = catchThrowable(() -> new UserBlackListGatewayImpl(null));

    assertThat(thrown).isExactlyInstanceOf(IllegalArgumentException.class);
    assertThat(thrown).hasMessageContaining("UserBlackListRepository is required");
  }
}
