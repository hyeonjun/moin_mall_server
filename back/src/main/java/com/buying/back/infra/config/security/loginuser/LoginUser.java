package com.buying.back.infra.config.security.loginuser;

import com.buying.back.application.account.domain.Account;
import java.util.Collections;
import lombok.Getter;
import org.springframework.security.core.userdetails.User;

@Getter
public class LoginUser extends User {

  private final Long id;
  private final String email;
  private final String name;

  public LoginUser(Account account) {
    super(account.getEmail(), account.getPassword(), Collections.emptySet());
    this.id = account.getAccountId();
    this.email = account.getEmail();
    this.name = account.getName();
  }
}
