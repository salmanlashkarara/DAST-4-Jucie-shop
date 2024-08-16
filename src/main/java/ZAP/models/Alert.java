package ZAP.models;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Alert {
  public String sourceid;
  public String other;
  public String method;
  public String evidence;
  public String pluginId;
  public String cweid;
  public String confidence;
  public String wascid;
  public String description;
  public String messageId;
  public String inputVector;
  public String url;
  private Map<String, String> tags;
  public String reference;
  public String solution;
  public String alert;
  public String param;
  public String attack;
  public String name;
  public String risk;
  public String id;
  public String alertRef;
}
