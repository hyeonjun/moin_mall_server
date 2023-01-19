package com.buying.back.application.product.controller.dto;

import lombok.Data;

public class StockDto {

    @Data
    public static class Create {
        private Long productId;
        private String optionCombination;
        private Integer quantity;
        private Integer price;
        private Integer discountPrice;
        private Integer discountRate;
    }

}
