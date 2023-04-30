package com.buying.back.application.product.controller.dto.brand;

import com.buying.back.util.verify.VerifyLengthUtil;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class CreateBrandProductDTO {

  @NotNull
  private Long categoryId;
  @NotBlank
  @Length(min = 1, max = VerifyLengthUtil.MAX_NAME_LENGTH)
  private String name;
  @NotNull
  @Min(value = 0)
  private Integer price;
  @Valid
  @Size(min = 1, max = 100)
  private List<CreateBrandItemDTO> itemsDto = new ArrayList<>();

}
