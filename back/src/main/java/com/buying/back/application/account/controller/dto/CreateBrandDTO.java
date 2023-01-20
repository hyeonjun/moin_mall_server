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
public class CreateBrandDTO {
  @Email
  @NotBlank
  @Length(min = 1, max = VerifyLengthUtil.MAX_EMAIL_LENGTH)
  private String email;
  @NotBlank
  @Length(min = 1, max = VerifyLengthUtil.MAX_NAME_LENGTH)
  private String name;

  @Setter
  private String password;

  @NotBlank
  @Length(min = 1, max = VerifyLengthUtil.MAX_NAME_LENGTH)
  private String brandName;

  @NotBlank
  @Length(min = 1, max = VerifyLengthUtil.MAX_NAME_LENGTH)
  private String president;

  @NotBlank
  private String roleType;

  @NotBlank
  @Length(min = 1, max = VerifyLengthUtil.MAX_DEFAULT_LENGTH)
  private String companyRegistrationNumber;

  private String siteUrl;
}
