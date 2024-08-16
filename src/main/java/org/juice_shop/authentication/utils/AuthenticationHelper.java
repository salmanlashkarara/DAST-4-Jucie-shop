package org.juice_shop.authentication.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.response.Response;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.juice_shop.Utils;
import org.juice_shop.authentication.model.Authentication;
import org.juice_shop.authentication.model.AuthenticationRequestBody;
import org.juice_shop.authentication.model.AuthenticationResponse;
import org.juice_shop.authentication.requests.AuthenticationRequests;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthenticationHelper {

  public static String getAuthenticationToken() {
    AuthenticationRequestBody authenticationRequestBody = new AuthenticationRequestBody();
    String payload = Utils.serializeToJson(authenticationRequestBody);
    Response res = AuthenticationRequests.getAdminToken(payload);
    AuthenticationResponse token = res.getBody().as(AuthenticationResponse.class);
    return token.getAuthentication().getToken();
  }

}
