package com.buying.back.application.account.controller.dto.brand;

import com.buying.back.util.verify.VerifyLengthUtil;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class CreateBrandCrewAccountDTO implements CreateBrandDTO {

  @NotBlank
  @Length(min = 11, max = VerifyLengthUtil.MAX_BUSINESS_LENGTH)
  private String businessNumber;
  @NotBlank
  @Length(min = 6, max = VerifyLengthUtil.MAX_PASSWORD_LENGTH)
  private String brandPassword;

  @Email
  @NotBlank
  @Length(min = 1, max = VerifyLengthUtil.MAX_EMAIL_LENGTH)
  private String accountEmail;
  @NotBlank
  @Length(min = 1, max = VerifyLengthUtil.MAX_NAME_LENGTH)
  private String accountName;
  @NotBlank
  @Length(min = 6, max = VerifyLengthUtil.MAX_PASSWORD_LENGTH)
  private String accountPassword;

}
