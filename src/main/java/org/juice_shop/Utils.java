package org.juice_shop;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public class Utils {

  public static String serializeToJson(final Object obj) {
    try {
      ObjectMapper mapper = new ObjectMapper();
      // Configure the mapper to exclude null values
      mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
      ObjectWriter writer = mapper.writerWithDefaultPrettyPrinter();
      return writer.writeValueAsString(obj);

    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

}
