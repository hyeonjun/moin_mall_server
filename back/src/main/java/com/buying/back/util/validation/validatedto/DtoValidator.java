package com.buying.back.util.validation.validatedto;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class DtoValidator implements Validator {

  @Override
  public boolean supports(Class<?> aClass) {
    return ValidateDTO.class.isAssignableFrom(aClass);
  }

  @Override
  public void validate(Object o, Errors errors) {
    // org.springframework.web.method.annotation.ModelAttributeMethodProcessor:163
    // org.springframework.validation.DataBinder:887
    if (!errors.hasErrors())  // LocalValidatorFactoryBean 으로 jsr303 검증을 마친 결과
      ((ValidateDTO) o).validate(errors);
  }

}


