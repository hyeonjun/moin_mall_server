package com.buying.back.application.account.controller.dto.brand;

import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdateBrandActivateDTO {

  @NotNull
  private Boolean activated;
}
