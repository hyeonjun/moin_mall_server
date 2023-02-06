package com.buying.back.infra.config.security;

import com.buying.back.application.account.domain.Account;
import com.buying.back.application.account.repository.AccountRepository;
import com.buying.back.infra.config.security.loginuser.LoginUser;
import com.buying.back.util.encryption.PasswordProvider;
import java.util.Arrays;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

  private final AccountRepository accountRepository;
  private final PasswordProvider passwordProvider;
  private final Environment environment;

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    String email = authentication.getName();
    String password = authentication.getCredentials().toString();

    // TODO: 2023/02/05 이후 mysql 사용 시에는 environment 체크하는 부분 삭제
    Account account;
    if (environment.getActiveProfiles()[0].equals("local")) {
      account = accountRepository.findByEmail(email)
        .orElseThrow(() -> new BadCredentialsException("id or password is wrong!"));
    } else {
      account = accountRepository.findByBinaryEmail(email)
        .orElseThrow(() -> new BadCredentialsException("id or password is wrong!"));
    }

    if (!passwordProvider.matches(password, account.getPassword())) {
      throw new BadCredentialsException("id or password is wrong!");
    }

    if (!account.isActivated()) {
      throw new AccountExpiredException("inactive user");
    }

    LoginUser loginUser = new LoginUser(account);

    return new UsernamePasswordAuthenticationToken(loginUser, loginUser.getPassword(),
      Collections.singleton(new SimpleGrantedAuthority(account.getRoleType().getValue())));
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return authentication.equals(UsernamePasswordAuthenticationToken.class);
  }
}
