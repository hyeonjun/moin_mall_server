package com.buying.back.application.account.service.vo;

import com.buying.back.application.account.code.type.RoleType;
import com.querydsl.core.annotations.QueryProjection;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BrandAccountManagementVO {

  private Long accountId;
  private String email;
  private String name;
  private RoleType role;
  private boolean activated;
  private LocalDateTime recentSignInDateTime;

  @QueryProjection
  public BrandAccountManagementVO(Long accountId, String email, String name, RoleType role,
    boolean activated, LocalDateTime recentSignInDateTime) {
    this.accountId = accountId;
    this.email = email;
    this.name = name;
    this.role = role;
    this.activated = activated;
    this.recentSignInDateTime = recentSignInDateTime;
  }
}
