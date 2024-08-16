package org.juice_shop.authentication.requests;

import static org.juice_shop.Environment.JUICE_SHOP_IP;
import static org.juice_shop.Environment.JUICE_SHOP_PORT;
import static org.juice_shop.Environment.ZAP_IP;
import static org.juice_shop.Environment.buildUrl;

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

  private static final String JUICE_SHOP_HOST = buildUrl(JUICE_SHOP_IP, JUICE_SHOP_PORT);


  @Step("Get Admin Token")
  public static Response getAdminToken(String payload) {
    String path = AuthenticationResources.GET_AUTHENTICATION_TOKEN.getValue();

    return RestAssured
        .given()
        .filters(new AllureRestAssured())
        .header("Content-Type", "application/json")
        .baseUri(JUICE_SHOP_HOST)
        .basePath(path)
        .proxy(ZAP_IP, 9090)
        .when()
        .body(payload)
        .post()
        .then()
        .statusCode(HttpStatus.SC_OK)
        .extract()
        .response();
  }

}
