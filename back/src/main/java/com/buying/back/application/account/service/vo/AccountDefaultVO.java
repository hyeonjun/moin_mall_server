package com.buying.back.application.account.service.vo;

import com.buying.back.application.account.code.type.AccountGradeType;
import com.buying.back.application.account.domain.Account;
import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AccountDefaultVO {

  protected Long accountId;
  protected String email;
  protected String name;
  protected LocalDate birthDay;

  protected String role;
  protected String grade;
  protected int addDiscount;
  protected int addAccumulate;

  protected boolean activated;

  public AccountDefaultVO(Account account) {
    this.accountId = account.getAccountId();
    this.email = account.getEmail();
    this.name = account.getName();

    this.birthDay = account.getBirthDay();

    this.role = account.getRoleType().getValue();

    AccountGradeType gradeType = account.getGradeType();
    this.grade = gradeType.getValue();
    this.addDiscount = gradeType.getAddDiscount();
    this.addAccumulate = gradeType.getAddAccumulate();

    this.activated = account.isActivated();
  }

  protected AccountDefaultVO(Long accountId, String email, String name, LocalDate birthDay,
    String role, String grade, int addDiscount, int addAccumulate, boolean activated) {
    this.accountId = accountId;
    this.email = email;
    this.name = name;
    this.birthDay = birthDay;
    this.role = role;
    this.grade = grade;
    this.addDiscount = addDiscount;
    this.addAccumulate = addAccumulate;
    this.activated = activated;
  }
}
