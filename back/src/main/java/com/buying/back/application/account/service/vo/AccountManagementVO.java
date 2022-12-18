package com.buying.back.application.account.service.vo;

import com.buying.back.application.account.code.type.AccountGradeType;
import com.buying.back.application.account.code.type.RoleType;
import com.buying.back.application.account.domain.Account;
import com.querydsl.core.annotations.QueryProjection;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AccountManagementVO extends AccountDefaultVO {

  protected LocalDateTime signUpDateTime;
  protected LocalDateTime recentSignInDateTime;

  public AccountManagementVO(Account account) {
    super(account);
    this.signUpDateTime = account.getSignUpDateTime();
    this.recentSignInDateTime = account.getRecentSignInDateTime();
  }

  @QueryProjection
  public AccountManagementVO(Long accountId, String email, String name, LocalDate birthDay,
    RoleType role, AccountGradeType grade, boolean activated,
    LocalDateTime signUpDateTime, LocalDateTime recentSignInDateTime) {
    super(accountId, email, name, birthDay, role, grade, activated);
    this.signUpDateTime = signUpDateTime;
    this.recentSignInDateTime = recentSignInDateTime;
  }
}
