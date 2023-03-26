package com.buying.back.application.account.code.exception;

import com.buying.back.application.common.exception.CommonException;
import com.buying.back.util.response.ResponseCode;

public class BrandException extends CommonException {


  public BrandException(BrandExceptionCode code) {
    super(code);
  }

  public BrandException(BrandExceptionCode code, String detailMessage) {
    super(code, detailMessage);
  }

  public BrandException(String detailMessage) {
    super(BrandExceptionCode.INVALID_BRAND, detailMessage);
  }

  public enum BrandExceptionCode implements ResponseCode {
    INVALID_BRAND("BEC-001", "invalid brand"),
    ALREADY_EXIST_BUSINESS_NUMBER("BEC-002", "already exist business number"),
    NOT_FOUND_BRAND("BEC-003", "brand not found"),
    NOT_MATCH_BRAND_PASSWORD("BEC-004", "brand password dose not match"),
    ALREADY_DEACTIVATED_BRAND("BED-005", "already deactivated brand"),
    ALREADY_ACTIVATED_BRAND("BED-006", "already activated brand")
    ;

    private final String code;
    private final String message;

    BrandExceptionCode(String code, String message) {
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
