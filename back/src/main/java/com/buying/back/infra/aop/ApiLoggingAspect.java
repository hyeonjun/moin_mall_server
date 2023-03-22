package com.buying.back.infra.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class ApiLoggingAspect {

  @Before("execution(* com.buying.back.application..controller.*Controller.*(..))")
  public void apiLogBefore(JoinPoint joinPoint) {
    HttpServletRequest request = getRequest();
    String apiURI = request.getRequestURI();
    String method = request.getMethod();
    long startTime = System.currentTimeMillis();

    request.setAttribute("startTime", startTime);

    if(HttpMethod.GET.matches(method)) {
      log.info("API: {}, HTTP Method: {}, Request Start Time: {}", apiURI, method, startTime);
    }
    if (HttpMethod.POST.matches(method) || HttpMethod.PUT.matches(method) || HttpMethod.DELETE.matches(method)) {
      String requestBody = Arrays.toString(joinPoint.getArgs());
      log.info("API: {}, HTTP Method: {}, Request Start Time: {}, Request Body: {}", apiURI, method, startTime, requestBody);
    }
  }

  @AfterReturning(value = "execution(* com.buying.back.application..controller.*Controller.*(..))", returning = "response")
  public void apiLogAfter(JoinPoint joinPoint, Object response) {
    HttpServletRequest request = getRequest();
    String apiURI = request.getRequestURI();
    String method = request.getMethod();
    long startTime = (long) request.getAttribute("startTime");
    long executeTime = System.currentTimeMillis() - startTime;

    log.info("API: {}, HTTP Method: {}, Execute Time: {}, Response Body: {}", apiURI, method, executeTime, response);
  }

  private HttpServletRequest getRequest() {
    return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
  }
}
