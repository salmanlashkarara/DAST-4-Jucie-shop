package org.juice_shop.authentication.requests;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.http.HttpStatus;
import org.juice_shop.authentication.resources.AuthenticationResources;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthenticationRequests {

  private final static String LOCAL_HOST = "http://localhost:3000";

  @Step("Get Admin Token")
  public static Response getAdminToken(String payload) {
    String path = AuthenticationResources.GET_AUTHENTICATION_TOKEN.getValue();

    return RestAssured
        .given()
        .header("Content-Type", "application/json")
        .baseUri(LOCAL_HOST)
        .basePath(path)
        .when()
        .body(payload)
        .post()
        .then()
        .statusCode(HttpStatus.SC_OK)
        .extract()
        .response();
  }

}
