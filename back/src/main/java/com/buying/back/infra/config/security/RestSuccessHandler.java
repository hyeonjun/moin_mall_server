package com.buying.back.infra.config.security;

import com.buying.back.infra.config.security.loginuser.LoginUser;
import com.buying.back.util.response.CommonResponse;
import com.buying.back.util.response.CommonResponseCode;
import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Objects;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Slf4j
@Component
public class RestSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

  private RequestCache requestCache = new HttpSessionRequestCache();
  private final int SESSION_DURATION_TIME = 30 * 60;

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
    Authentication authentication) throws IOException, ServletException {

    SavedRequest savedRequest = requestCache.getRequest(request, response);
    if (Objects.isNull(savedRequest)) {
      requestCache.removeRequest(request, response);
    }

    clearAuthenticationAttributes(request); // 에러 세션을 지우는 메서드 실행

    LoginUser user = null;
    if (Objects.nonNull(SecurityContextHolder.getContext().getAuthentication())) {
      Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      if (principal instanceof LoginUser) {
        user = (LoginUser) principal;
      }
    }

    // login user's session duration is 30 minutes.
    HttpSession session = request.getSession();
    session.setMaxInactiveInterval(SESSION_DURATION_TIME);
    if (Objects.nonNull(user)) {
      session.setAttribute("email", ":"+user.getEmail()); // split을 위해 ":" 넣음
    }

    response.setHeader("session-duration-time", String.valueOf(SESSION_DURATION_TIME));

    MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
    MediaType jsonMimeType = MediaType.APPLICATION_JSON;
    CommonResponse responseData;
    if (user == null) {
      responseData = new CommonResponse(CommonResponseCode.FAIL);
    } else {
      responseData = new CommonResponse(user, CommonResponseCode.SUCCESS);
    }
    jsonConverter.write(responseData, jsonMimeType, new ServletServerHttpResponse(response));
  }

  @Override
  protected void handle(HttpServletRequest request, HttpServletResponse response,
    Authentication authentication) throws IOException, ServletException {
    SavedRequest savedRequest = requestCache.getRequest(request, response);
    if (Objects.nonNull(savedRequest)) {
      String targetUrlParam = getTargetUrlParameter();
      if (isAlwaysUseDefaultTargetUrl() ||
        (Objects.nonNull(targetUrlParam) &&
          StringUtils.hasText(request.getParameter(targetUrlParam)))) {
        requestCache.removeRequest(request, response);
      }
    }
    clearAuthenticationAttributes(request);
  }
}
