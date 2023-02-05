package com.buying.back.application.inquiry.contoller;

import com.buying.back.application.account.code.type.RoleType;
import com.buying.back.application.account.controller.dto.CreateAccountDTO;
import com.buying.back.application.account.domain.Account;
import com.buying.back.application.account.repository.AccountRepository;
import com.buying.back.infra.config.security.CustomAuthenticationProvider;
import com.buying.back.infra.config.security.loginuser.LoginUser;
import com.buying.back.util.encryption.PasswordProvider;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Order(101)
@TestComponent("testCommonAuthProvider")
public class MockAuthProvider extends CustomAuthenticationProvider {


  public MockAuthProvider(AccountRepository accountRepository,
    PasswordProvider passwordProvider) {
    super(accountRepository, passwordProvider);
  }

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    String email = authentication.getName();
    String password = authentication.getCredentials().toString();
    return authenticate(new UsernamePasswordAuthenticationToken(email, password));
  }

  public Authentication authenticate(String email, String password, RoleType roleType)
    throws AuthenticationException {

    List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
    String role = roleType.getValue();
    grantedAuthorityList.add(new SimpleGrantedAuthority("ROLE_" + role));

    Account account = Account.testAccount()
      .id(1L)
      .email(email)
      .name("사용자")
      .password(password)
      .roleType(roleType)
      .build();

    LoginUser loginUser = new LoginUser(account);
    return new UsernamePasswordAuthenticationToken(loginUser, password, grantedAuthorityList);
  }
}