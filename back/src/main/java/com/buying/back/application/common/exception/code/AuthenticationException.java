package com.buying.back.application.common.exception.code;

import com.buying.back.application.common.exception.CommonException;
import com.buying.back.util.response.ResponseCode;

public class AuthenticationException extends CommonException {

  public AuthenticationException(AuthenticationExceptionCode code) {
    super(code);
  }

  public AuthenticationException(AuthenticationExceptionCode code, String resource) {
    super(code, resource);
  }

  public AuthenticationException(String detailMessage) {
    super(AuthenticationExceptionCode.NOT_AUTHENTICATED, detailMessage);
  }

  public enum AuthenticationExceptionCode implements ResponseCode {
    NOT_AUTHENTICATED("ATE-001", "not authenticated"),
    HANDLE_ACCESS_DENIED("ATE-002", "access is denied"),
    NOT_AUTHORIZED("ATE-003", "not authorized"),
    BAD_CREDENTIALS("ATE-004", "BAD CREDENTIALS"),
    INACTIVE_USER("ATE-005", "inactive user"),
    ;

    private final String code;
    private final String message;

    AuthenticationExceptionCode(String code, String message) {
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
