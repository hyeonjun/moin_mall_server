package com.buying.back.infra.config.security;

import com.buying.back.application.account.domain.Account;
import com.buying.back.application.account.helper.AccountHelper;
import com.buying.back.application.account.repository.AccountRepository;
import com.buying.back.infra.config.security.loginuser.LoginUser;
import com.buying.back.util.encryption.PasswordProvider;
import java.util.Collection;
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
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

  private final AccountRepository accountRepository;
  private final AccountHelper accountHelper;
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
      getAuthorities(loginUser.getId()));
  }

  private Collection<GrantedAuthority> getAuthorities(Long accountId) {
    Pageable pageable = PageRequest.of(0, 1000);
    return new HashSet<>(accountHelper.getAssignedRoleByAccount(accountId, pageable));
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return authentication.equals(UsernamePasswordAuthenticationToken.class);
  }
}
