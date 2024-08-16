package org.juice_shop;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.juice_shop.authentication.utils.AuthenticationHelper;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AuthenticationTest {

  @Test
  public void authenticationTest  () {
    String token = AuthenticationHelper.getAuthenticationToken();
    Assert.assertNotNull(token);
  }

}
