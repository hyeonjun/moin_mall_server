package com.buying.back.application.inquiry.contoller;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

public class ParamConvertUtil {

  public static MultiValueMap<String, String> convert(Object dto) {
    MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
    try {
      for (Field field : dto.getClass().getDeclaredFields()) {
        field.setAccessible(true);
        Optional.ofNullable(field.get(dto))
          .ifPresent(value -> {
            List<String> values = value instanceof Collection<?>
              ? ((Collection<?>) value).stream().map(Object::toString).collect(Collectors.toList())
              : Collections.singletonList(value.toString());
            params.put(field.getName(), values);
          });
      }
    } catch (Exception e) {
      throw new IllegalStateException("Parameter 변환 중 오류 발생", e);
    }
    return params;
  }

}

