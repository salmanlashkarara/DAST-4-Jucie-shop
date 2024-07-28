package org.juice_shop.authentication.resources;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AuthenticationResources {

  GET_AUTHENTICATION_TOKEN("/rest/user/login");

  private String value;

}
