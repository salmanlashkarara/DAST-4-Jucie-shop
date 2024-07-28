package org.juice_shop.products.utils;

import io.restassured.response.Response;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.juice_shop.Utils;
import org.juice_shop.products.model.ProductData;
import org.juice_shop.products.model.Products;
import org.juice_shop.products.requests.ProductRequests;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductHelper {

  public static void updateProduct(int productId, double newPrice) {
    ProductData productData = new ProductData();
    productData.setPrice(newPrice);
    productData.setId(productId);
    String payload = Utils.serializeToJson(productData);
    ProductRequests.updateProduct(productId, payload);
  }

  public static Products getProducts() {
    Response res = ProductRequests.getProducts();
    return res.getBody().as(Products.class);
  }

}
