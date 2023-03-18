package com.buying.back.application.account.service.vo;

import com.buying.back.application.account.code.type.AccountGradeType;
import com.buying.back.application.account.code.type.RoleType;
import com.buying.back.application.account.domain.Account;
import java.time.LocalDate;
import java.util.Optional;

import com.buying.back.application.account.domain.Brand;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AccountDefaultVO {

  protected Long accountId;
  protected String email;
  protected String name;
  protected LocalDate birthDay;

  protected RoleType role;
  protected AccountGradeType grade;
  protected int addDiscount;
  protected int addAccumulate;

  protected boolean activated;

  public static AccountDefaultVO valueOf(Account account) {
    AccountDefaultVO vo = new AccountDefaultVO();

    vo.setAccountId(account.getId());
    vo.setEmail(account.getEmail());
    vo.setName(account.getName());
    vo.setBirthDay(account.getBirthDay());
    vo.setRole(account.getRoleType());

    Optional.ofNullable(account.getGradeType())
      .ifPresent(gradeType -> {
        vo.setGrade(gradeType);
        vo.setAddDiscount(gradeType.getAddDiscount());
        vo.setAddAccumulate(gradeType.getAddAccumulate());
      }
    );
    vo.setActivated(account.isActivated());
    return vo;
  }

  protected AccountDefaultVO(Long accountId, String email, String name, LocalDate birthDay,
    RoleType role, AccountGradeType grade, boolean activated) {
    this.accountId = accountId;
    this.email = email;
    this.name = name;
    this.birthDay = birthDay;
    this.role = role;
    this.grade = grade;
    this.activated = activated;
  }
}
