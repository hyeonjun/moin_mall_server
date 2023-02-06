package com.buying.back.application.account.controller.dto;

import com.buying.back.application.account.code.type.RoleType;
import com.buying.back.util.verify.VerifyLengthUtil;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.net.URL;

// json 역직렬화를 위해서 setter, getter 사용
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

  private String password;

  @NotBlank
  @Length(min = 1, max = VerifyLengthUtil.MAX_NAME_LENGTH)
  private String brandName;

  @NotBlank
  @Length(min = 1, max = VerifyLengthUtil.MAX_DEFAULT_LENGTH)
  private String representativeName;

  // TODO: 2023-01-21 쉘 스크립트 공격 service에서 막는 로직 구현 필요
  private RoleType roleType;

  @NotBlank
  @Length(min = 11, max = VerifyLengthUtil.MAX_BUSINESS_LENGTH)
  private String businessNumber;

  // TODO: 2023-01-21 서비스 로직에서 url 검증하고 검증 실패 시 에러 메시지 반환 코드 구현
  private String url;
}
