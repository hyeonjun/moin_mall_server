package com.buying.back.application.product.controller.dto;

import com.buying.back.application.product.code.ItemStatus;
import lombok.Data;

import java.util.List;

public class ItemDto {

    @Data
    public static class Create {
        private String name;
        private String options;
        private Integer quantity;
        private Integer price;
        private Integer discountPrice;
        private Integer discountRate;
        private List<OptionDto.Create> optionsDto;
    }

    @Data
    public static class Update {
        private Long itemId;
        private String name;
        private Integer quantity;
        private Integer price;
        private Integer discountPrice;
        private Integer discountRate;
    }
}
