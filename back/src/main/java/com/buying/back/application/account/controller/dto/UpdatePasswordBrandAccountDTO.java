package com.buying.back.application.account.controller.dto;

import com.buying.back.application.account.code.type.RoleType;
import com.buying.back.util.verify.VerifyLengthUtil;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UpdatePasswordBrandAccountDTO {
  @NotBlank
  private String password;

}
