package com.buying.back.application.product.domain;

import com.buying.back.application.product.controller.dto.ItemDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Item {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "item_id")
  private Long id;
  private String name;
  private String options;
  private Integer quantity;
  private Integer price;
  private Integer discountPrice;
  private Integer discountRate;

  @Setter
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "product_id", nullable = false, referencedColumnName = "product_id")
  @JsonBackReference
  private Product product;

  @Builder
  private Item(String name, String options, Integer quantity, Integer price, Integer discountPrice,
    Integer discountRate, Product product) {
    this.name = name;
    this.options = options;
    this.quantity = quantity;
    this.price = price;
    this.discountPrice = discountPrice;
    this.discountRate = discountRate;
    this.product = product;
  }

  public static Item create(ItemDto.Create dto, Product product) {
    return Item.builder()
      .name(dto.getName())
      .options(dto.getOptions())
      .quantity(dto.getQuantity())
      .price(dto.getPrice())
      .discountPrice(dto.getDiscountPrice())
      .discountRate(dto.getDiscountRate())
      .product(product)
      .build();
  }

  public void updateItem(ItemDto.Update dto) {
    this.name = dto.getName();
    this.price = dto.getPrice();
    this.quantity = dto.getQuantity();
    this.discountPrice = dto.getDiscountPrice();
    this.discountRate = dto.getDiscountRate();
  }

  public Set<Long> getOptionIds() {
    return Arrays.stream(this.options.split("/"))
      .map(Long::valueOf)
      .collect(Collectors.toSet());
  }
}
