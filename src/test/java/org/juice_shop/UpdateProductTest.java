package org.juice_shop;


import org.juice_shop.products.model.ProductData;
import org.juice_shop.products.model.Products;
import org.juice_shop.products.utils.ProductHelper;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UpdateProductTest {


  public static final double NEW_PRICE = 505.55;
  public static final int PRODUCT_ID = 5;

  /*
   * Scenario: Update Product
   *
   * Given: There are several different products
   * When: The user updates a single product
   * Then: the attributes of product is updated
   * */

  @Test
  public void testUpdateProduct() {

    // Given: There are several different products
    Products products = ProductHelper.getProducts();

    // When: The user updates a single product
    int actualProductId = products.getProductData().get(PRODUCT_ID).getId();
    ProductHelper.updateProduct(actualProductId, NEW_PRICE);

    // Then: the attributes of product is updated
    products = ProductHelper.getProducts();
    ProductData actualUpdatedProduct = products.getProductData()
        .stream()
        .filter(productData -> productData.getId() == actualProductId).findFirst().orElseThrow();
    Assert.assertEquals(actualUpdatedProduct.getPrice(), NEW_PRICE);


  }
}
