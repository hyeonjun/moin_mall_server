package com.buying.back.application.product.controller.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

public class ItemDto {

  @Getter
  @Setter
  public static class Create {


  }

  @Getter
  @Setter
  public static class Update {

    @NotNull
    private Long itemId;
    private String options;
    @Min(value = 0)
    private Integer stockQuantity;
    @Min(value = 0)
    private Integer additionalPrice;
    @Min(value = 0)
    private Integer discountPrice;
    @Min(value = 0)
    private Double discountRate;
  }
}
