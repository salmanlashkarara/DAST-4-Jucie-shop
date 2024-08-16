package org.juice_shop;

import ZAP.helper.ZapHelper;
import ZAP.models.Alert;
import ZAP.requests.ZapRequest;
import java.util.List;
import java.util.Set;
import org.testng.annotations.Test;

public class DynamicApplicationSecurityTest {

  @Test(enabled = false)
  public void dast() {
    List<String> contexts = ZapHelper.getZapContexts().getContextList();
    String zapContextName = contexts.get(0);
    ZapRequest.includeServicesInZapContext(zapContextName);

    String zapContextId = ZapHelper.getZapContextId(zapContextName);

    String scanId = ZapHelper.runActiveScanAgainstZapContext(zapContextId);
    ZapHelper.waitUntilZapScanCompletes(scanId);

    // Get messages of each alert
    ZapHelper.logRequestAndResponsesOfZapAttacks();

    // Get and update the risk level of false-positive alerts

    Set<Alert> alertsToIgnore = ZapHelper.selectFalsePositiveAlerts();
    ZapHelper.labelAlertsAsFalsePositive(alertsToIgnore);

    ZapHelper.saveHtmlReport();
  }

}
