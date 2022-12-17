package com.buying.back.infra.aop;

import java.lang.reflect.Method;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Slf4j
@Aspect
@Component
public class ExecutionTimeLoggingAOP {

  @Around("execution(* com.buying.back.application..service.*Service.*(..))")
  public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
    String className = getClassName(joinPoint);
    String methodName = getMethodName(joinPoint);
    List<String> parameterNames = getParameter(joinPoint);

    Object proceed;
    StopWatch stopWatch = new StopWatch();
    try {
      stopWatch.start();
      proceed = joinPoint.proceed();
    } finally {
      long time = stopWatch.getTotalTimeMillis();
      long executionTimeLimit = 2000L;
      if (time > executionTimeLimit) {
        log.warn("{}.{}.{}: {} ms", className, methodName, parameterNames, time);
      }
    }
    return proceed;
  }

  private String getClassName(JoinPoint joinPoint) {
    Class<?> activeClass = joinPoint.getTarget().getClass();
    return activeClass.getName();
  }

  private String getMethodName(JoinPoint joinPoint) {
    MethodSignature signature = (MethodSignature) joinPoint.getSignature();
    Method method = signature.getMethod();
    return method.getName();
  }

  private List<String> getParameter(JoinPoint joinPoint) {
    return Stream.of(joinPoint.getArgs())
      .map(String::valueOf)
      .collect(Collectors.toList());
  }
}

/**
 * Aspect
 * 여러 클래스들에 영향을 미치는 로직의 모듈화
 * 로그, 트랜잭션 관리처럼 여러 클래스들에 미치는 로직을 공통화하여 분리한 것
 *
 * Advice
 * 여러 join point 중 특정 join point 에서 발생되는 공통적인 기능
 * 포인트컷과 매치되는 어느 join point 에서든 실행됨
 * 특정 함수의 이름을 지정하면 함수가 실행될 때마다 advice 도 동작
 *
 * JoinPoint
 * 메서드 실행 혹은 에러 처리 시 프로그램에서 발생하는 에러의 지점
 * 스프링 AOP 에서의 join point 는 항상 메서드 실행을 나타냄
 *
 * Pointcut
 * join point 와 매치되는 부분을 설정
 * => Point cut 으로 어떤 Join Point 에만 Advice 를 동작시킬 것인지 설정할 수 있다.
 *
 * Target object
 * 하나 혹은 여러개의 Aspect에 의해 동작되는 객체
 * 스프링 AOP는 런타임 프록시에 의해 실행되기 때문에 이 객체는 항상 프록시 객체
 *
 * AOP proxy
 * Advice 메서드 실행 등과 같은 Aspect 내용을 수행하기 위해 AOP 프레임워크가 만든 프록시 객체
 * 스프링 프레임워크에서 AOP 프록시는 JDK dynamic 프록시가 디폴트로, 어떤 인터페이스든 프록시화될 수 있다.
 * CGLIB 프록시도 사용가능하나 CGLIB 는 인터페이스를 사용하지 않고 클래스를 사용할 때 디폴트다.
 *
 * Weaving
 * Aspect를 연결 Target object와 연결하여 AOP 객체로 만드는 것
 * 다른 자바 AOP 프레임워크와 스프링 AOP는 런타임 때 실행
 */
