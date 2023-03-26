package com.buying.back.application.account.controller.dto.brand;

import com.buying.back.util.verify.VerifyLengthUtil;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class UpdateBrandInfoDTO {

  @Length(min = 1, max = VerifyLengthUtil.MAX_NAME_LENGTH)
  private String brandName;
  @Length(min = 1, max = VerifyLengthUtil.MAX_DEFAULT_LENGTH)
  private String representativeName;
  @Email
  @Length(min = 1, max = VerifyLengthUtil.MAX_EMAIL_LENGTH)
  private String representativeEmail;
  @Length(min = 11, max = VerifyLengthUtil.MAX_BUSINESS_LENGTH)
  private String businessNumber;
  @Length(min = 1, max = VerifyLengthUtil.MAX_URL_LENGTH)
  private String url;

  @NotBlank
  private String confirmBrandPassword;

}
