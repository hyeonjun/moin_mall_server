package com.buying.back.application.product.service.vo;

import com.buying.back.application.account.domain.Brand;
import com.buying.back.application.category.domain.Category;
import com.buying.back.application.product.domain.Product;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductVO {

  private Long productId;
  private String name;
  private Integer price;

  private Long brandId;
  private Long categoryId;
  private String categoryName;

  protected ProductVO(Product product) {
    this.productId = product.getId();
    this.name = product.getName();
    this.price = product.getPrice();

    Brand brand = product.getBrand();
    this.brandId = brand.getId();

    Category category = product.getCategory();
    this.categoryId = category.getId();
    this.categoryName = category.getName();
  }

  public static ProductVO valueOf(Product product) {
    return new ProductVO(product);
  }

  @QueryProjection
  public ProductVO(Long productId, String name, Integer price, Long brandId, Long categoryId,
    String categoryName) {
    this.productId = productId;
    this.name = name;
    this.price = price;
    this.brandId = brandId;
    this.categoryId = categoryId;
    this.categoryName = categoryName;
  }
}
