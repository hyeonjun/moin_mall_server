package com.buying.back.application.product.repository.param;

import com.buying.back.application.account.domain.Brand;
import com.buying.back.application.product.controller.dto.ProductDto;
import com.buying.back.application.product.controller.dto.SearchProductDTO;
import com.buying.back.application.product.controller.dto.brand.SearchBrandProductDTO;
import java.util.Objects;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class SearchProductListParam {

  private String productName;

  private Long categoryId;
  private Long brandId;

  private Integer priceFrom;
  private Integer priceTo;

  private LocalDate createdDateFrom;
  private LocalDate createdDateTo;

  private Boolean deleted;

  public static SearchProductListParam valueOf(SearchProductDTO dto) {
    SearchProductListParam param = new SearchProductListParam();
    param.setProductName(dto.getProductName());
    param.setCategoryId(dto.getCategoryId());
    param.setPriceFrom(dto.getPriceFrom());
    param.setPriceTo(dto.getPriceTo());
    return param;
  }

  public static SearchProductListParam valueOf(SearchBrandProductDTO dto, Brand brand) {
    SearchProductListParam param = SearchProductListParam.valueOf(dto);
    param.setDeleted(dto.getDeleted());

    if (Objects.nonNull(brand)) {
      param.setBrandId(brand.getId());
    }

    param.setCreatedDateFrom(dto.getCreatedDateFrom());
    param.setCreatedDateTo(dto.getCreatedDateTo());

    return param;
  }
}
