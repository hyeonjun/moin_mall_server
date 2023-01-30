package com.buying.back.application.product.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;

public class OptionDto {
    @Data
    @NoArgsConstructor
    public static class Create {
        @NotBlank
        private String optionName;
        @NotBlank
        private String optionValue;
        private Integer orderBy;
    }
}
