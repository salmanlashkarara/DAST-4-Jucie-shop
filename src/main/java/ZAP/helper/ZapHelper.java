package ZAP.helper;

import ZAP.models.Alert;
import ZAP.models.Alerts;
import ZAP.models.ZapContexts;
import ZAP.requests.ZapRequest;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.awaitility.Awaitility;
import org.awaitility.core.ConditionEvaluationLogger;

@Slf4j
public class ZapHelper {


  public static final String REPORT_FILE_PATH = "./penetration-report/dast-report.html";
  private static final List<String> FALSE_POSITIVE_ALERTS_NAMES = List.of("SQL Injection");

  private ZapHelper() {
  }

  @Step("Trigger ZAP scan")
  public static String runActiveScanAgainstZapContext(String zapContextId) {
    String scanId = ZapRequest.runActiveScanAgainstZapContext(zapContextId);
    return getValueFromJsonPath(scanId, "/scan");
  }

  @Step("Log request and responses of each Attack")
  public static void logRequestAndResponsesOfZapAttacks() {
    Alerts alerts = getAlertIds();
    for (Alert alert : alerts.getAlerts()) {
      ZapRequest.getMessages(alert.getMessageId(), alert.getName());
    }
  }

  @Step("Save ZAP report as HTML file")
  public static void saveHtmlReport() {
    String report = ZapRequest.getHtmlReport();
    Path path = Paths.get(REPORT_FILE_PATH);
    try {
      Path parentDir = path.getParent();
      if (parentDir != null) {
        Files.createDirectories(parentDir);
      }

      Files.writeString(path, report);
      log.info("File saved successfully at: " + path.toAbsolutePath());
    } catch (IOException e) {
      log.error("Error saving file: " + e.getMessage());
    }
  }

  // Wait maximum 5 hours for pen test
  @Step("Wait until ZAP scan is completed")
  public static void waitUntilZapScanCompletes(String zapScanId) {

    Awaitility.await("ZapScan is not completed")
        .atMost(Duration.of(300, ChronoUnit.MINUTES))
        .pollInterval(Duration.of(1, ChronoUnit.MINUTES))
        .ignoreExceptions()
        .conditionEvaluationListener(new ConditionEvaluationLogger(log::info))
        .until(() -> getScanCompletionPercentage(zapScanId).contains("100"));
  }

  @Step("Get ZAP scan progress percentage")
  private static String getScanCompletionPercentage(String zapScanId) {
    return ZapRequest.getScanCompletionPercentage(zapScanId);
  }

  @Step("Get ZAP context")
  public static String getZapContextId(String contextName) {
    String rawRes = ZapRequest.getContext(contextName);
    return getValueFromJsonPath(rawRes, "/context/id");
  }

  public static Alerts getAlertIds() {
    Response rawRes = ZapRequest.getAlerts();
    return rawRes.getBody().as(Alerts.class);
  }

  public static boolean isUrlOfPublicService(Alert alert, String serviceName) {
    return alert.getUrl().contains(serviceName);
  }

  @Step("Select False-Positive alerts")
  public static Set<Alert> selectFalsePositiveAlerts() {
    Alerts alerts = getAlertIds();
    Set<Alert> alertsToIgnore = new HashSet<>();
    for (Alert alert : alerts.getAlerts()) {

      // If the URL of alert is not from public service then the alert should be ignored
      if (!(isUrlOfPublicService(alert, "vehicle-service")
          || isUrlOfPublicService(alert, "fde-service")
          || isUrlOfPublicService(alert, "fapi"))) {

        alertsToIgnore.add(alert);
      }

      // If the alert level is "Informational" then the alert should be ignored
      if (alert.getRisk().toLowerCase().contains("Informational".toLowerCase())) {
        alertsToIgnore.add(alert);
      }

      // Alerts should be ignored
      for (String alertName : FALSE_POSITIVE_ALERTS_NAMES) {

        if (alert.getDescription().toLowerCase().contains(alertName.toLowerCase())) {
          alertsToIgnore.add(alert);
        }
      }
    }
    return alertsToIgnore;
  }

  @Step("Label alerts as false-positive")
  public static void labelAlertsAsFalsePositive(Set<Alert> alertsToIgnore) {
    for (Alert alert : alertsToIgnore.stream().toList()) {
      log.info(
          String.format(
              "Alert URL: %s Alert Risk:  %s ", alert.getUrl().substring(0, 20), alert.getRisk()));

      ZapRequest.updateAlertToFalsePositive(Integer.parseInt(alert.getId()));
    }
  }

  public static ZapContexts getZapContexts() {
    Response rawRes = ZapRequest.getContexts();
    return rawRes.getBody().as(ZapContexts.class);
  }

  public static String getValueFromJsonPath(String json, String jsonPath) {
    try {
      ObjectMapper mapper = new ObjectMapper();
      JsonNode node = mapper.readTree(json);
      JsonNode coordinatesNode = node.at(jsonPath);
      if (coordinatesNode != null) {
        return coordinatesNode.asText();
      }
      return null;
    } catch (Exception e) {
      log.error("Error in getting value from jsonpath: " + e.getMessage());
      return null;
    }
  }
}

