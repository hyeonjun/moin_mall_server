package com.buying.back.application.account.controller.dto.account;

import com.buying.back.util.verify.VerifyLengthUtil;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class CheckAccountPasswordDTO {

  @NotBlank
  @Length(min = 6, max = VerifyLengthUtil.MAX_PASSWORD_LENGTH)
  private String password;

}
