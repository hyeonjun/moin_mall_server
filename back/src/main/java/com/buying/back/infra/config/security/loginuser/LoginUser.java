package com.buying.back.infra.config.security.loginuser;

import com.buying.back.application.account.domain.Account;

import java.time.LocalDateTime;
import java.util.Collections;
import lombok.Getter;
import org.springframework.security.core.userdetails.User;

@Getter
public class LoginUser extends User {

  private final Long accountId;
  private final String email;
  private final String name;
  private final LocalDateTime recentPasswordUpdateDateTime;
  private final Long brandId;

  public LoginUser(Account account) {
    super(account.getEmail(), account.getPassword(), Collections.emptySet());
    this.accountId = account.getId();
    this.email = account.getEmail();
    this.name = account.getName();
    this.recentPasswordUpdateDateTime = account.getRecentPasswordUpdateDateTime();
    this.brandId = account.getBrand().getId();
  }
}
