package com.buying.back.application.common.exception.code;

import com.buying.back.application.common.exception.CommonException;
import com.buying.back.util.response.ResponseCode;

public class InvalidInputValueException extends CommonException {

  public InvalidInputValueException(InvalidInputValueExceptionCode code) {
    super(code);
  }

  public InvalidInputValueException(InvalidInputValueExceptionCode code, String detailMessage) {
    super(code, detailMessage);
  }

  public InvalidInputValueException(String detailMessage) {
    super(InvalidInputValueExceptionCode.INVALID_INPUT_VALUE, detailMessage);
  }

  public enum InvalidInputValueExceptionCode implements ResponseCode {

    INVALID_INPUT_VALUE("IIV-001", "invalid input value"),
    MISMATCHED_PASSWORD("IIV-002", "mismatched password"),
    METHOD_ARGUMENT_TYPE_MISMATCH("IIV-03", "method argument type mismatch");

    private final String code;
    private final String message;

    InvalidInputValueExceptionCode(String code, String message) {
      this.code = code;
      this.message = message;
    }

    @Override
    public String getCode() {
      return code;
    }

    @Override
    public String getMessage() {
      return message;
    }

  }









}
