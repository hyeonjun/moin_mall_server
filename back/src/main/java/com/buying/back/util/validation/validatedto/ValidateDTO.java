package com.buying.back.util.validation.validatedto;

import org.springframework.validation.Errors;

public interface ValidateDTO {

  void validate(Errors e);

}
