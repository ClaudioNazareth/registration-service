package com.example.registration.service.usecases;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;

import org.junit.Test;

public class VerifyBlackListTest {

  @Test
  public void should_throw_IllegalArgumentException_when_UserBlackListGateway_is_null() {

    Throwable thrown = catchThrowable(() -> new VerifyBlackList(null));

    assertThat(thrown).isExactlyInstanceOf(IllegalArgumentException.class);
    assertThat(thrown).hasMessageContaining("UserBlackListGateway is required");
  }
}
