package com.buying.back.application.account.service.vo;

import com.buying.back.application.account.code.type.AccountGradeType;
import com.buying.back.application.account.code.type.RoleType;
import com.buying.back.application.account.domain.Account;
import java.time.LocalDate;
import java.util.Optional;

import com.buying.back.application.account.domain.Brand;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
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

  public AccountDefaultVO(Account account) {
    this.accountId = account.getId();
    this.email = account.getEmail();
    this.name = account.getName();

    this.birthDay = account.getBirthDay();

    this.role = account.getRoleType();

    Optional.ofNullable(account.getGradeType())
      .ifPresent(gradeType -> {
        this.grade = gradeType;
        this.addDiscount = gradeType.getAddDiscount();
        this.addAccumulate = gradeType.getAddAccumulate();
      }
    );
    this.activated = account.isActivated();
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
