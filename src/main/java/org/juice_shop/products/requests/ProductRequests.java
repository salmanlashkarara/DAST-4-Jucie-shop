package org.juice_shop.products.requests;

import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.http.HttpStatus;
import org.juice_shop.products.resources.ProductResources;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductRequests {

  private static final String LOCAL_HOST = "http://172.18.0.2:3000";
  private static final String ZAP_HOST = "172.18.0.4:9090";

  @Step("Update single product")
  public static void updateProduct(int productId, String payload) {
    String path = ProductResources.UPDATE_PRODUCT.getValue();

    RestAssured
        .given()
        .filters(new AllureRestAssured())
        .header("Content-Type", "application/json")
        .baseUri(LOCAL_HOST)
        .basePath(path)
        .pathParam("id", productId)
        .proxy(ZAP_HOST)
        .when()
        .body(payload)
        .put()
        .then()
        .statusCode(HttpStatus.SC_OK)
        .extract()
        .response();
  }


  @Step("Get all products")
  public static Response getProducts() {
    String path = ProductResources.GET_PRODUCTS.getValue();

    return RestAssured
        .given()
        .filters(new AllureRestAssured())
        .header("Content-Type", "application/json")
        .baseUri(LOCAL_HOST)
        .basePath(path)
        .proxy(ZAP_HOST)
        .when()
        .get()
        .then()
        .statusCode(HttpStatus.SC_OK)
        .extract()
        .response();
  }
}
