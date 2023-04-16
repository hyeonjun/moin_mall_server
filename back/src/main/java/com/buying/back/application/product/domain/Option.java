package com.buying.back.application.product.domain;

import com.buying.back.application.product.controller.dto.OptionDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import java.time.LocalDateTime;
import java.util.Objects;
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
public class Option {

  @Id
  @Column(name = "option_id")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String name;
  private String value;
  private Integer orderBy;

  @Setter
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "product_id", nullable = false, referencedColumnName = "product_id")
  @JsonBackReference
  private Product product;

  private boolean deleted;
  private LocalDateTime deletedAt;

  @Builder
  private Option(String name, String value, Integer orderBy, Product product) {
    this.name = name;
    this.value = value;
    this.orderBy = orderBy;
    this.product = product;
  }

  public static Option create(OptionDto.Create dto, Product product) {
    return Option.builder()
      .name(dto.getOptionName())
      .value(dto.getOptionValue())
      .orderBy(dto.getOrderBy())
      .product(product)
      .build();
  }

  public void optionDelete() {
    this.deleted = true;
    this.deletedAt = LocalDateTime.now();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Option)) {
      return false;
    }
    Option option = (Option) o;
    return Objects.equals(getId(), option.getId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
