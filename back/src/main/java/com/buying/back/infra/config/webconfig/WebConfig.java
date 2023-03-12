package com.buying.back.infra.config.webconfig;

import com.buying.back.infra.config.webconfig.inquiry.converter.InquiryChildTypeConverter;
import com.buying.back.infra.config.webconfig.inquiry.converter.InquiryParentTypeConverter;
import com.buying.back.infra.interceptor.QueryCountLoggingInterceptor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

  private final QueryCountLoggingInterceptor queryCountLoggingInterceptor;

  @Override
  public void addFormatters(FormatterRegistry registry) {
    WebMvcConfigurer.super.addFormatters(registry);
    registry.addConverter(new InquiryParentTypeConverter());
    registry.addConverter(new InquiryChildTypeConverter());
  }

  @Profile("local")
  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**")
      .allowedOriginPatterns("*")
      .allowedMethods("GET", "POST", "PUT", "DELETE")
      .allowedHeaders("*")
      .allowCredentials(true);
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    log.info("register interceptor!!!");
    registry.addInterceptor(queryCountLoggingInterceptor)
      .addPathPatterns("/**"); // 해당 경로 접하기 전에 인터셉터가 가로챔
    //.excludePathPatterns("/**"); // 해당 경로는 가로채지 않음
  }
}
