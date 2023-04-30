package com.buying.back.application.product.controller.dto.brand;

import com.buying.back.util.validation.validatedto.ValidateDTO;
import com.buying.back.util.validation.validatedto.product.ItemDTOValidationCondition;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.Errors;

@Getter
@Setter
public class UpdateBrandItemDTO implements ValidateDTO {

  private String options;
  @Min(value = 0)
  private Integer stockQuantity;
  @Min(value = 0)
  private Integer additionalPrice;
  @Min(value = 0)
  private Integer discountPrice;
  @Min(value = 0)
  private Double discountRate;


  @Override
  public void validate(Errors e) {
    ItemDTOValidationCondition.checkOptionSize(e, this.options);
  }
}
