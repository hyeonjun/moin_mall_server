package com.buying.back.infra.config;

import static com.fasterxml.jackson.databind.SerializationFeature.FAIL_ON_EMPTY_BEANS;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonGenerator.Feature;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

@Configuration
public class ObjectMapperConfig {

  @Primary
  @Bean("defaultObjectMapper")
  public ObjectMapper objectMapper() {
    return Jackson2ObjectMapperBuilder.json()
      .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
      .featuresToEnable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
      .modules(new JavaTimeModule())
      .visibility(PropertyAccessor.ALL, Visibility.NONE)
      .visibility(PropertyAccessor.FIELD, Visibility.ANY)
      .featuresToEnable(Feature.WRITE_BIGDECIMAL_AS_PLAIN)
      .build();
  }

  @Bean
  public HttpMessageConverter<?> customMappingJackson2HttpMessageConverter() {
    return new MappingJackson2HttpMessageConverter(objectMapper());
  }

  @Bean("debugAopObjectMapper")
  public ObjectMapper objectMapperAOP() {
    return Jackson2ObjectMapperBuilder.json()
      .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
      .modules(new JavaTimeModule())
      .featuresToEnable(Feature.WRITE_BIGDECIMAL_AS_PLAIN)
      .featuresToDisable(FAIL_ON_EMPTY_BEANS)
      .build();
  }

}
