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
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

@Component
public class RestAccessDeniedHandler implements AccessDeniedHandler {

  @Override
  public void handle(HttpServletRequest request, HttpServletResponse response,
    AccessDeniedException accessDeniedException) throws IOException, ServletException {
    MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
    CommonResponse<CommonResponseCode> responseData = new CommonResponse<>(
      AuthenticationExceptionCode.HANDLE_ACCESS_DENIED);
    response.setStatus(200);
    jsonConverter.write(responseData, MediaType.APPLICATION_JSON, new ServletServerHttpResponse(response));
  }
}
