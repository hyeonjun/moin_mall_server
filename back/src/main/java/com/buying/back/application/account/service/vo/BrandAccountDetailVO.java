package com.buying.back.application.account.service.vo;

import com.buying.back.application.account.code.type.RoleType;
import com.buying.back.application.account.domain.Account;
import com.buying.back.application.account.domain.Brand;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BrandAccountDetailVO extends BrandDetailVO {

  private Long accountId;
  private String accountEmail;
  private String accountName;

  private RoleType role;

  private boolean activated;

  public static BrandAccountDetailVO valueOf(Brand brand, Account account) {
    BrandAccountDetailVO vo = new BrandAccountDetailVO(brand);

    vo.setAccountId(account.getId());
    vo.setAccountEmail(account.getEmail());
    vo.setAccountName(account.getName());
    vo.setRole(account.getRoleType());
    vo.setActivated(account.isActivated());

    return vo;
  }

  private BrandAccountDetailVO(Brand brand) {
    super(brand);
  }
}
