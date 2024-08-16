package org.juice_shop.authentication.requests;

import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.http.HttpStatus;
import org.juice_shop.authentication.resources.AuthenticationResources;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthenticationRequests {

  private static final String JUICE_SHOP_HOST = "http://172.18.0.2:3000";
  private static final String ZAP_HOST = "http://172.18.0.4:9090";

  @Step("Get Admin Token")
  public static Response getAdminToken(String payload) {
    String path = AuthenticationResources.GET_AUTHENTICATION_TOKEN.getValue();

    return RestAssured
        .given()
        .filters(new AllureRestAssured())
        .header("Content-Type", "application/json")
        .baseUri(JUICE_SHOP_HOST)
        .basePath(path)
        .proxy(ZAP_HOST)
        .when()
        .body(payload)
        .post()
        .then()
        .statusCode(HttpStatus.SC_OK)
        .extract()
        .response();
  }

}
