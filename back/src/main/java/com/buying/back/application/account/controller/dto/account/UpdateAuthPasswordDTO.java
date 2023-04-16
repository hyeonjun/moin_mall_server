package com.buying.back.application.account.controller.dto.account;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateAuthPasswordDTO extends UpdateAccountPasswordDTO {

  @NotBlank
  private String email;
  @NotBlank
  private String code; // 인증번호
}
