package com.buying.back.application.product.domain;

import com.buying.back.application.common.domain.Base;
import com.buying.back.application.product.controller.dto.ProductDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(
    indexes = {
        @Index(columnList = "name")
    }
)
@Entity
public class Product extends Base {
    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 191, nullable = false)
    private String name;
    @Column(nullable = false)
    private Long categoryId;
    @Column(nullable = false)
    private Long brandId;

    @Builder
    private Product(String name, Long categoryId, Long brandId) {
        this.name = name;
        this.categoryId = categoryId;
        this.brandId = brandId;
    }

    public static Product createProduct(ProductDto.Create create) {
        return Product.builder()
                .name(create.getName())
                .brandId(create.getBrandId())
                .categoryId(create.getCategoryId())
                .build();
    }
}
