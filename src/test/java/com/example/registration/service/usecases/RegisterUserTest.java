package com.example.registration.service.usecases;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;

import com.example.registration.service.gateways.UserGateway;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class RegisterUserTest {

  @Mock private UserGateway userGateway;

  @Mock private ExclusionService exclusionService;

  @Test
  public void should_throw_IllegalArgumentException_when_UserGateway_is_null() {

    Throwable thrown = catchThrowable(() -> new RegisterUser(null, exclusionService));

    assertThat(thrown).isExactlyInstanceOf(IllegalArgumentException.class);
    assertThat(thrown).hasMessageContaining("UserGateway is required");
  }

  @Test
  public void should_throw_IllegalArgumentException_when_ExclusionService_is_null() {

    Throwable thrown = catchThrowable(() -> new RegisterUser(userGateway, null));

    assertThat(thrown).isExactlyInstanceOf(IllegalArgumentException.class);
    assertThat(thrown).hasMessageContaining("ExclusionService is required");
  }
}
