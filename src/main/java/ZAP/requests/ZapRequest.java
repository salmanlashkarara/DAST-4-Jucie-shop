package ZAP.requests;


import static org.juice_shop.Environment.ZAP_IP;
import static org.juice_shop.Environment.ZAP_PORT;
import static org.juice_shop.Environment.buildUrl;

import ZAP.resources.ZapResource;
import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;

public class ZapRequest {

  public static final String CONTEXT_NAME_parameter = "contextName";
  private static final String ZAP_URL = buildUrl(ZAP_IP, ZAP_PORT);

  private ZapRequest() {
  }

  @Step("Include URLs in ZAP context")
  public static String includeServicesInZapContext(String contextName) {

    String path = ZapResource.INCLUDE_URLS_IN_CONTEXT.getValue();

    return RestAssured
        .given()
        .filters(new AllureRestAssured())
        .header("Content-Type", "application/json")
        .baseUri(ZAP_URL)
        .basePath(path)
        .queryParam(CONTEXT_NAME_parameter, contextName)
        .queryParam("regex", ".*172\\.18\\.0\\.2.*") // Include only these services for scanning
        .queryParam("apikey", "your_api_key_here")
        .when()
        .get()
        .then()
        .statusCode(HttpStatus.SC_OK)
        .extract()
        .response()
        .asString();
  }

  public static String runActiveScanAgainstZapContext(String contextId) {

    String path = ZapResource.RUN_ACTIVE_SCAN.getValue();

    return RestAssured
        .given()
        .filters(new AllureRestAssured())
        .header("Content-Type", "application/json")
        .baseUri(ZAP_URL)
        .basePath(path)
        .queryParam("contextId", contextId)
        .queryParam("inScopeOnly", true)
        .queryParam("apikey", "your_api_key_here")
        .when()
        .get()
        .then()
        .statusCode(HttpStatus.SC_OK)
        .extract()
        .response()
        .asString();
  }

  public static String getScanCompletionPercentage(String scanId) {

    String path = ZapResource.GET_SCAN_COMPLETION_PERCENTAGE.getValue();

    return RestAssured
        .given()
        .filters(new AllureRestAssured())
        .header("Content-Type", "application/json")
        .baseUri(ZAP_URL)
        .basePath(path)
        .queryParam("inScopeOnly", true)
        .param("scanId", scanId)
        .queryParam("apikey", "your_api_key_here")
        .when()
        .get()
        .then()
        .statusCode(HttpStatus.SC_OK)
        .extract()
        .response()
        .asString();
  }

  public static String getHtmlReport() {

    String path = ZapResource.GET_HTML_REPORT.getValue();

    return RestAssured
        .given()
        .filters(new AllureRestAssured())
        .header("Content-Type", "application/json")
        .baseUri(ZAP_URL)
        .basePath(path)
        .queryParam("inScopeOnly", true)
        .queryParam("apikey", "your_api_key_here")
        .when()
        .get()
        .then()
        .statusCode(HttpStatus.SC_OK)
        .extract()
        .response()
        .asString();
  }

  public static String getContext(String zapContextName) {
    String path = ZapResource.VIEW_CONTEXT.getValue();

    return RestAssured
        .given()
        .filters(new AllureRestAssured())
        .header("Content-Type", "application/json")
        .baseUri(ZAP_URL)
        .basePath(path)
        .param(CONTEXT_NAME_parameter, zapContextName)
        .queryParam("apikey", "your_api_key_here")
        .when()
        .get()
        .then()
        .statusCode(HttpStatus.SC_OK)
        .extract()
        .response()
        .asString();
  }

  @Step("Get list of ZAP's contexts")
  public static Response getContexts() {
    String path = ZapResource.GET_CONTEXTS.getValue();

    return RestAssured
        .given()
        .filters(new AllureRestAssured())
        .header("Content-Type", "application/json")
        .baseUri(ZAP_URL)
        .basePath(path)
        .queryParam("apikey", "your_api_key_here")
        .when()
        .get()
        .then()
        .statusCode(HttpStatus.SC_OK)
        .extract()
        .response();

  }

  public static Response getAlerts() {
    String path = ZapResource.GET_ALERTS.getValue();

    return RestAssured
        .given()
        .filters(new AllureRestAssured())
        .header("Content-Type", "application/json")
        .baseUri(ZAP_URL)
        .basePath(path)
        .queryParam("apikey", "your_api_key_here")
        .when()
        .get()
        .then()
        .statusCode(HttpStatus.SC_OK)
        .extract()
        .response();
  }

  public static Response updateAlertToFalsePositive(int alertId) {
    String path = ZapResource.UPDATE_ALERT.getValue();

    return RestAssured
        .given()
        .filters(new AllureRestAssured())
        .header("Content-Type", "application/json")
        .baseUri(ZAP_URL)
        .basePath(path)
        .queryParam("id", alertId)
        .queryParam("name", "Ignored alert")
        .queryParam("riskId", 0)
        .queryParam("confidenceId", 0)
        .queryParam("description", "False-positive alert")
        .queryParam("apikey", "your_api_key_here")
        .when()
        .get()
        .then()
        .statusCode(HttpStatus.SC_OK)
        .extract()
        .response();
  }

  @Step("Get ZAP request and response messages for {alertName}")
  public static Response getMessages(String messageId, String alertName) {
    String path = ZapResource.GET_ALERT_MESSAGES.getValue();
    return RestAssured
        .given()
        .filters(new AllureRestAssured())
        .header("Content-Type", "application/json")
        .baseUri(ZAP_URL)
        .basePath(path)
        .queryParam("ids", messageId)
        .queryParam("apikey", "your_api_key_here")
        .when()
        .get()
        .then()
        .statusCode(HttpStatus.SC_OK)
        .extract()
        .response();
  }
}
