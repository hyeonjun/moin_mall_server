package com.buying.back.application.product.controller.dto;

import com.buying.back.util.verify.VerifyLengthUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

public class OptionDto {
    @Data
    @AllArgsConstructor
    public static class Create {
        @NotBlank
        private String optionName;
        @NotBlank
        private String optionValue;
        private Long productId;
    }
}
