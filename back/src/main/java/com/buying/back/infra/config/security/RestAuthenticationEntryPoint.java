package com.buying.back.infra.config.security;

import com.buying.back.application.common.exception.code.AuthenticationException.AuthenticationExceptionCode;
import com.buying.back.util.response.CommonResponse;
import com.buying.back.util.response.CommonResponseCode;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response,
    AuthenticationException authException) throws IOException, ServletException {
    MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
    CommonResponse<CommonResponseCode> responseData =
      new CommonResponse<>(AuthenticationExceptionCode.NOT_AUTHORIZED);
    response.setStatus(200);
    jsonConverter.write(responseData, MediaType.APPLICATION_JSON,
      new ServletServerHttpResponse(response));
  }
}
