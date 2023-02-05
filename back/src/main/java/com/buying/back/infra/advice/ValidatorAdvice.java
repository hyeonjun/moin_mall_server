package com.buying.back.infra.advice;

import com.buying.back.util.validation.validatedto.DtoValidator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ValidatorAdvice {

  private static final DtoValidator validator = new DtoValidator();

  @InitBinder
  public void initBinder(WebDataBinder webDataBinder) {
    if (webDataBinder.getTarget() != null
      && validator.supports(webDataBinder.getTarget().getClass())) {
      webDataBinder.addValidators(validator);
    }
  }

}
