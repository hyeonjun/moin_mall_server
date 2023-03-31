package com.buying.back.application.product.service.vo;

import com.buying.back.application.product.domain.Product;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDefaultVO {
    private Long productId;
    private String name;
    private Integer price;
    // TODO: 2023-02-02 Category Response Add
    // TODO: 2023-02-02 Brand Response  Add

    public ProductDefaultVO(Product product) {
        this.productId = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
    }

    @QueryProjection
    private ProductDefaultVO(Long productId, String name, Integer price) {
        this.productId = productId;
        this.name = name;
        this.price = price;
    }
}
