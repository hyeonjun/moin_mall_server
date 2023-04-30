package com.buying.back.application.product.controller.dto.brand;

import com.buying.back.util.validation.validatedto.ValidateDTO;
import com.buying.back.util.validation.validatedto.product.ItemDTOValidationCondition;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.Errors;

@Getter
@Setter
public class CreateBrandItemDTO implements ValidateDTO {

  @NotBlank
  private String options;
  @NotNull
  @Min(value = 0)
  private Integer stockQuantity;
  @NotNull
  @Min(value = 0)
  private Integer additionalPrice;
  @NotNull
  @Min(value = 0)
  private Integer discountPrice;
  @NotNull
  @Min(value = 0)
  private Double discountRate;

  @Override
  public void validate(Errors e) {
    ItemDTOValidationCondition.checkOptionSize(e, this.options);
  }
}
