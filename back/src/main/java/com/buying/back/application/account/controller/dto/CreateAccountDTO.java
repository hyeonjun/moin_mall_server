package com.buying.back.application.account.controller.dto;

import com.buying.back.util.verify.VerifyLengthUtil;
import java.time.LocalDate;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Getter
@Setter
public class CreateAccountDTO {

  @Email
  @NotBlank
  @Length(min = 1, max = VerifyLengthUtil.MAX_EMAIL_LENGTH)
  private String email;
  @NotBlank
  @Length(min = 1, max = VerifyLengthUtil.MAX_NAME_LENGTH)
  private String name;
  @NotBlank
  @Length(min = 1, max = VerifyLengthUtil.MAX_PASSWORD_LENGTH)
  private String password;
  @NotNull
  @DateTimeFormat(iso = ISO.DATE)
  private LocalDate birthDay;

}
