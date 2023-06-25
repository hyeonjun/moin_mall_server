package com.buying.back.infra.aop;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class DebugServiceHelperLoggingAOP {

  private final ObjectMapper debugAopObjectMapper;

  public DebugServiceHelperLoggingAOP(
    @Qualifier("debugAopObjectMapper") ObjectMapper debugAopObjectMapper) {
    this.debugAopObjectMapper = debugAopObjectMapper;
  }

  @Pointcut("execution(* com.buying.back.application..service.*Service.*(..))")
  public void servicePointcut() {}
  @Pointcut("execution(* com.buying.back.application..helper.*Helper.*(..))")
  public void helperPointcut() {}

  @Before("servicePointcut() || helperPointcut()")
  public void logBefore(JoinPoint joinPoint) {
    if (log.isDebugEnabled()) {
      try {
        CodeSignature codeSignature = (CodeSignature) joinPoint.getSignature();
        String[] names = codeSignature.getParameterNames();
        Object[] args = joinPoint.getArgs();
        Map<String, String> typeValue = new HashMap<>();

        for (int i = 0; i < names.length; i++) {
          typeValue.put(names[i], args[i] == null ? "null" : debugAopObjectMapper.writeValueAsString(args[i]));
        }

        log.debug(
          "Before Method run [method={}.{}, parameter={}]",
          joinPoint.getSignature().getDeclaringTypeName(),
          joinPoint.getSignature().getName(),
          typeValue
        );
      } catch (JsonProcessingException e) {
        log.error(e.getMessage());
      }
    }
  }

  @AfterReturning(value = "servicePointcut() || helperPointcut()", returning = "result")
  public void logAfter(JoinPoint joinPoint, Object result) {
    if (log.isDebugEnabled() && result != null) {
      try {
        log.debug(
          "After Method run [method={}.{}, type={}, result={}]",
          joinPoint.getSignature().getDeclaringTypeName(),
          joinPoint.getSignature().getName(),
          result.getClass().getSimpleName(),
          debugAopObjectMapper.writeValueAsString(result)
        );
      } catch (JsonProcessingException e) {
        log.error(e.getMessage());
      }
    }
  }

}

