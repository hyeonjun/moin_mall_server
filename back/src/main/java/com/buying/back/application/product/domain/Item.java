package com.buying.back.application.product.domain;

import static com.buying.back.application.product.service.ItemService.ITEM_OPTION_DELIMITER;

import com.buying.back.application.common.domain.Base;
import com.buying.back.application.product.controller.dto.ItemDto;
import com.buying.back.application.product.controller.dto.brand.CreateBrandItemDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
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
import lombok.Setter;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(
  name = "item",
  indexes = {
    @Index(columnList = "product_id, options")
  }
)
public class Item extends Base {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "item_id")
  private Long id;
  private String options;

  @Column(name = "stock_quantity", nullable = false)
  private Integer stockQuantity;
  @Column(name = "additional_price", nullable = false)
  private Integer additionalPrice;
  @Column(name = "discount_price", nullable = false)
  private Integer discountPrice;
  @Column(name = "discount_rate", nullable = false)
  private Double discountRate;

  @Setter
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "product_id", nullable = false, referencedColumnName = "product_id")
  @JsonBackReference
  private Product product;

  private boolean deleted;
  private LocalDateTime deletedAt;

  @Builder
  private Item(String options, Integer stockQuantity, Integer additionalPrice, Integer discountPrice,
    Double discountRate, Product product) {
    this.options = options;
    this.stockQuantity = stockQuantity;
    this.additionalPrice = additionalPrice;
    this.discountPrice = discountPrice;
    this.discountRate = discountRate;
    this.product = product;
  }

  public static Item create(CreateBrandItemDTO dto, Product product) {
    return Item.builder()
      .options(dto.getOptions())
      .stockQuantity(dto.getStockQuantity())
      .additionalPrice(dto.getAdditionalPrice())
      .discountPrice(dto.getDiscountPrice())
      .discountRate(dto.getDiscountRate())
      .product(product)
      .build();
  }

  public void updateItem(ItemDto.Update dto) {
    this.options = dto.getOptions();
    this.additionalPrice = dto.getAdditionalPrice();
    this.stockQuantity = dto.getStockQuantity();
    this.discountPrice = dto.getDiscountPrice();
    this.discountRate = dto.getDiscountRate();
  }

  public void deleteItem() {
    this.deleted = true;
    this.deletedAt = LocalDateTime.now();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Item)) {
      return false;
    }
    Item item = (Item) o;
    return Objects.equals(getId(), item.getId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
