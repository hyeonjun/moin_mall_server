package com.buying.back.application.product.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

public class OptionDto {
    @Data
    @AllArgsConstructor
    public static class Create {
        @NotBlank
        private String optionName;
        @NotBlank
        private String optionValue;
    }
}
