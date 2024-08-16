package ZAP.resources;

import lombok.AllArgsConstructor;
import lombok.Getter;

/** NOTE: Despite normal APIs the OWASP ZAP Proxy does not need a host:port in its URL */
@Getter
@AllArgsConstructor
public enum ZapResource {
  GET_CONTEXTS("/JSON/context/view/contextList/"),
  INCLUDE_URLS_IN_CONTEXT("/JSON/context/action/includeInContext/"),
  VIEW_CONTEXT("/JSON/context/view/context/"),
  RUN_ACTIVE_SCAN("/JSON/ascan/action/scan/"),
  GET_SCAN_COMPLETION_PERCENTAGE("/JSON/ascan/view/status/"),
  GET_ALERTS("/JSON/core/view/alerts/"),
  UPDATE_ALERT("/JSON/alert/action/updateAlert/"),
  GET_ALERT_MESSAGES("/JSON/core/view/messagesById/"),
  GET_HTML_REPORT("/OTHER/core/other/htmlreport/");
  private String value;
}
