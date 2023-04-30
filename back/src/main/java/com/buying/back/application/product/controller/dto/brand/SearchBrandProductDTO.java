package com.buying.back.application.product.controller.dto.brand;

import com.buying.back.application.product.controller.dto.SearchProductDTO;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchBrandProductDTO extends SearchProductDTO {

  private LocalDate createdDateFrom;
  private LocalDate createdDateTo;
  private Boolean deleted;
}
