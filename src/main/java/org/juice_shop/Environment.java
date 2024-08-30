package org.juice_shop;

public class Environment {

  public static final String LOCAL_HOST = "localhost";

  public static final String JUICE_SHOP_IP = "172.18.0.2";
  public static final String JUICE_SHOP_PORT = "3000";

  public static final String ZAP_IP = "172.18.0.4";
  public static final String ZAP_PORT = "9090";
  public static final String ZAP_API_KEY = "d62b2555-ff68-4e51-82de-9bbe195761f4";


  public static String buildUrl(String host, String port){

    return String.format("http://%s:%s/", host, port);
  }
}
