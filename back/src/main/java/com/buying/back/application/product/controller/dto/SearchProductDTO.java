package com.buying.back.application.product.controller.dto;

import com.buying.back.application.common.dto.PagingDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchProductDTO extends PagingDTO {

  private String productName;
  private Long categoryId;
  private Integer priceFrom;
  private Integer priceTo;
}
