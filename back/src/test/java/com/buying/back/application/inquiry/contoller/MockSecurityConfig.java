package com.buying.back.application.inquiry.contoller;

import com.buying.back.application.account.repository.AccountRepository;
import com.buying.back.infra.config.security.CustomAuthenticationProvider;
import com.buying.back.infra.config.security.LogoutSuccessHandler;
import com.buying.back.infra.config.security.RestAccessDeniedHandler;
import com.buying.back.infra.config.security.RestAuthenticationEntryPoint;
import com.buying.back.infra.config.security.RestFailureHandler;
import com.buying.back.infra.config.security.RestSuccessHandler;
import com.buying.back.util.encryption.PasswordProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;

@TestConfiguration
public class MockSecurityConfig {

  @MockBean
  CustomAuthenticationProvider customAuthenticationProvider;
  // 권한이 없는 상태에서 요청시 발생 (인가)
  @MockBean
  private RestAuthenticationEntryPoint restAuthenticationEntryPoint;
  @MockBean
  private RestSuccessHandler restSuccessHandler;
  // 인증되지 않은 상황에서 호출시 발생 (인증)
  @MockBean
  private RestFailureHandler restFailureHandler;
  @MockBean
  private RestAccessDeniedHandler restAccessDeniedHandler;
  @MockBean
  private LogoutSuccessHandler logoutSuccessHandler;

  @MockBean
  public PasswordProvider passwordProvider;
  @MockBean
  public AccountRepository accountRepository;
}
