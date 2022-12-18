package com.buying.back.infra.config.security.loginuser;

import com.buying.back.application.account.repository.AccountRepository;
import com.buying.back.application.common.exception.code.AccountException;
import com.buying.back.application.common.exception.code.AccountException.AccountExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

  private final AccountRepository accountRepository;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    return accountRepository.findByEmail(email)
      .map(LoginUser::new)
      .orElseThrow(() -> new AccountException(AccountExceptionCode.NOT_FOUND_ACCOUNT));
  }
}
