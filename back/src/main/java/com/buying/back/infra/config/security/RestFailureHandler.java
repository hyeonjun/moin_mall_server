package com.buying.back.infra.config.security;

import com.buying.back.application.common.exception.code.AuthenticationException.AuthenticationExceptionCode;
import com.buying.back.util.response.CommonResponse;
import com.buying.back.util.response.CommonResponseCode;
import com.buying.back.util.response.ResponseCode;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component
public class RestFailureHandler extends SimpleUrlAuthenticationFailureHandler {

  @Override
  public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
    AuthenticationException exception) throws IOException, ServletException {
    MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
    ResponseCode responseCode = CommonResponseCode.FAIL;
    if(exception instanceof BadCredentialsException) {
      responseCode = AuthenticationExceptionCode.BAD_CREDENTIALS;
    } else if (exception instanceof AccountExpiredException) {
      responseCode = AuthenticationExceptionCode.INACTIVE_USER;
    }
    jsonConverter.write(new CommonResponse<>(responseCode), MediaType.APPLICATION_JSON,
      new ServletServerHttpResponse(response));
  }
}
