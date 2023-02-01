package com.buying.back.application.product.controller.dto;

import lombok.Data;

import java.util.List;

public class ProductDto {

    @Data
    public static class Create {
        /*
        * 카테고리는 이미 만들어진 것 ( 따로 API 동작 )
        * Product - Option - Stock
        * */
        private String name;
        private Integer price;
        private Long brandId;
        private Long categoryId;
        private List<ItemDto.Create> itemsDto;
    }

    @Data
    public static class Update {
        private String name;
        private Long categoryId;
        private Integer price;
        private List<ItemDto.Update> itemsDto;
    }
}
