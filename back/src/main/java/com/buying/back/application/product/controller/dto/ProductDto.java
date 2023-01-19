package com.buying.back.application.product.controller.dto;

import lombok.Data;

public class ProductDto {

    @Data
    public static class Create {
        private String name;
        private Long brandId;
        private Long categoryId;
    }
}
