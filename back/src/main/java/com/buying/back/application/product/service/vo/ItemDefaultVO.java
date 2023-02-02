package com.buying.back.application.product.service.vo;

import java.util.List;

public class ItemDefaultVO {
    private Long id;
    private String name;
    private Integer price;
    private Integer quantity;
    private Integer discountPrice;
    private Integer discountRate;
    private List<ItemOptionVO> options;

    public ItemDefaultVO(Long id, String name, Integer price, Integer quantity, Integer discountPrice, Integer discountRate, List<ItemOptionVO> options) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.discountPrice = discountPrice;
        this.discountRate = discountRate;
        this.options = options;
    }
}
