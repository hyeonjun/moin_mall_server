package com.buying.back.application.product.domain;

import com.buying.back.application.account.domain.Brand;
import com.buying.back.application.category.domain.Category;
import com.buying.back.application.common.domain.Base;
import com.buying.back.application.product.controller.dto.ProductDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(
  name = "product",
  indexes = {
    @Index(columnList = "product_name")
  }
)
@Entity
public class Product extends Base {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "product_id")
  private Long id;

  @ManyToOne
  @JoinColumn(name = "category_id", updatable = false, referencedColumnName = "category_id")
  @JsonBackReference
  private Category category; // 카테고리는 항상 같이 조회
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "brand_id", updatable = false, referencedColumnName = "brand_id")
  @JsonBackReference
  private Brand brand; // 따로 조회할 수도 있을 수 있으니 Lazy

  @Column(name = "product_name", nullable = false, length = 191)
  private String name;
  @Column(name = "product_price", nullable = false)
  private Integer price;
  private boolean deleted;
  private LocalDateTime deletedAt;

  @Builder
  private Product(String name, Integer price, Brand brand, Category category) {
    this.category = category;
    this.brand = brand;
    this.name = name;
    this.price = price;
  }

  public static Product create(ProductDto.Create dto, Brand brand, Category category) {
    return Product.builder()
      .name(dto.getName())
      .brand(brand)
      .category(category)
      .price(dto.getPrice())
      .build();
  }

  public void update(ProductDto.Update dto, Category category) {
    this.name = dto.getName();
    this.price = dto.getPrice();

    if (category != null) {
      this.category = category;
    }
  }

  public void delete() {
    this.deleted = true;
    this.deletedAt = LocalDateTime.now();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Product)) {
      return false;
    }
    Product product = (Product) o;
    return Objects.equals(getId(), product.getId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
