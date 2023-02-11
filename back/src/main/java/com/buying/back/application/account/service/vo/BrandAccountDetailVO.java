package com.buying.back.application.account.service.vo;

import com.buying.back.application.account.code.type.RoleType;
import com.buying.back.application.account.domain.Account;
import com.buying.back.application.account.domain.Brand;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BrandAccountDetailVO extends BrandDetailVO {

  private Long accountId;
  private String accountEmail;
  private String accountName;

  private RoleType role;

  private boolean activated;

  public BrandAccountDetailVO(Brand brand, Account account) {
    super(brand);
    this.accountId = account.getId();
    this.accountEmail = account.getEmail();
    this.accountName = account.getName();
    this.role = account.getRoleType();
    this.activated = account.isActivated();
  }
}
