package com.buying.back.application.account.controller.dto.account;

import com.buying.back.util.verify.VerifyLengthUtil;
import javax.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class UpdateAccountDTO {

  @Email
  @Length(min = 1, max = VerifyLengthUtil.MAX_EMAIL_LENGTH)
  private String email;
  @Length(min = 1, max = VerifyLengthUtil.MAX_NAME_LENGTH)
  private String name;

}
