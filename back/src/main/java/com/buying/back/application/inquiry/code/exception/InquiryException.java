package com.buying.back.application.inquiry.code.exception;

import com.buying.back.application.common.exception.CommonException;
import com.buying.back.util.response.ResponseCode;

public class InquiryException extends CommonException {

  public InquiryException(InquiryExceptionCode code) {
    super(code);
  }

  public InquiryException(InquiryExceptionCode code, String detailMessage) {
    super(code, detailMessage);
  }

  public InquiryException(String detailMessage) {
    super(InquiryExceptionCode.INVALID_INQUIRY, detailMessage);
  }

  public enum InquiryExceptionCode implements ResponseCode {
    INVALID_INQUIRY("IQE-001", "invalid inquiry"),
    WRONG_INQUIRY_TYPE("IQE-002", "wrong inquiry type"),
    NOT_FOUND_INQUIRY("IQE-003", "not found inquiry"),
    NOT_AUTHORIZED("IQE-004", "not authorized access to the inquiry"),
    ALREADY_DELETED("IQE-005", "already deleted inquiry"),
    ALREADY_REPLY_ANSWER("IQE-006", "already has answered inquiry")
    ;

    private final String code;
    private final String message;

    InquiryExceptionCode(String code, String message) {
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
