package org.juice_shop.products.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductData {
  private int id;
  private String name;
  private String description;
  private double price;
  private double deluxePrice;
  private String image;
  private String createdAt;
  private String updatedAt;
  private Object deletedAt;
}
