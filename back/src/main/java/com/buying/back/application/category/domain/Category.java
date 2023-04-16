package com.buying.back.application.category.domain;

import com.buying.back.application.product.controller.dto.CategoryDto;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Category {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "category_id")
  private Long id;
  private String name;
  private Long parentId; // parent category_id -> primary key

  @Builder
  public Category(String name, Long parentId) {
    this.name = name;
    this.parentId = parentId;
  }

  public static Category create(CategoryDto.Create create) {
    return Category.builder()
      .name(create.getName())
      .parentId(create.getParentId())
      .build();
  }

}
