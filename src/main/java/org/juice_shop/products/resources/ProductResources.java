package org.juice_shop.products.resources;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProductResources {
  GET_PRODUCTS("/rest/products/search"),
  UPDATE_PRODUCT("/api/Products/{id}");

  private String value;

}
