package com.buying.back.application.product.controller.dto.brand;

import com.buying.back.util.verify.VerifyLengthUtil;
import javax.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class UpdateBrandProductDTO {

  private Long categoryId;
  @Length(min = 1, max = VerifyLengthUtil.MAX_NAME_LENGTH)
  private String name;
  @Min(value = 0)
  private Integer price;

}
