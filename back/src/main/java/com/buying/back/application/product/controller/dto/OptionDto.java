package com.buying.back.application.product.controller.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

public class OptionDto {
    @Data
    public static class Create {
        @NotBlank
        private String optionName;
        @NotBlank
        private String optionValue;
        private Integer orderBy;
    }

    @Data
    public static class Update {
        private Long optionId;
        private String optionName;
        private String optionValue;
        private String orderBy;
    }
}
