package com.buying.back.application.account.service.vo;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BrandAccountManagementDetailVO extends BrandAccountManagementVO {

  private LocalDateTime signUpDateTime;
  private LocalDateTime recentPasswordUpdateDateTime;



}
