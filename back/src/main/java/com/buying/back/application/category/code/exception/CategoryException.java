package com.buying.back.application.category.code.exception;

import com.buying.back.application.common.exception.CommonException;
import com.buying.back.util.response.ResponseCode;

public class CategoryException extends CommonException {


  public CategoryException(CategoryExceptionCode code) {
    super(code);
  }

  public CategoryException(CategoryExceptionCode code, String detailMessage) {
    super(code, detailMessage);
  }

  public CategoryException(String detailMessage) {
    super(CategoryExceptionCode.INVALID_CATEGORY, detailMessage);
  }

  public enum CategoryExceptionCode implements ResponseCode {
    INVALID_CATEGORY("CATE-001", "invalid category"),
    NOT_FOUND_CATEGORY("CATE-002", "not found category"),
    ;

    private final String code;
    private final String message;

    CategoryExceptionCode(String code, String message) {
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
