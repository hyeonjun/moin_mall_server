package com.buying.back.util.email.provider.exception;

import com.buying.back.application.common.exception.CommonException;
import com.buying.back.util.response.ResponseCode;

public class AwsEmailException extends CommonException {

  public AwsEmailException(ResponseCode responseCode) {
    super(responseCode);
  }

  public AwsEmailException(ResponseCode responseCode, String detailMessage) {
    super(responseCode, detailMessage);
  }

  public AwsEmailException(String detailMessage) {
    super(AwsExceptionCode.INVALID_AWS_SERVICE, detailMessage);
  }

  public enum AwsExceptionCode implements ResponseCode {
    INVALID_AWS_SERVICE("AWS-001", "invalid aws service"),
    NOT_SENT_AWS_EMAIL("AWS-002", "email was not sent normally"),
    ;

    private final String code;
    private final String message;

    AwsExceptionCode(String code, String message) {
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
