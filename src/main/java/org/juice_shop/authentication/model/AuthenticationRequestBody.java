package org.juice_shop.authentication.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AuthenticationRequestBody {

  private String email;
  private String password;

  public AuthenticationRequestBody() {
    this.email = "admin@juice-sh.op";
    this.password = "admin123";
  }

}
