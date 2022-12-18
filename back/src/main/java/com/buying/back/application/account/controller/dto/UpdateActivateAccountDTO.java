package com.buying.back.application.account.controller.dto;

import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateActivateAccountDTO {

  @NotNull
  private Boolean activated;

}
