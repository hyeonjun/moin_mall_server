package com.buying.back.infra.config.security;

import com.buying.back.application.account.domain.Account;
import com.buying.back.application.account.repository.AccountRepository;
import com.buying.back.infra.config.security.loginuser.LoginUser;
import com.buying.back.util.encryption.PasswordProvider;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

  private final AccountRepository accountRepository;
  private final PasswordProvider passwordProvider;

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    String email = authentication.getName();
    String password = authentication.getCredentials().toString();

    Account account = accountRepository.findByEmail(email)
      .orElseThrow(() -> new BadCredentialsException("id or password is wrong!"));

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
