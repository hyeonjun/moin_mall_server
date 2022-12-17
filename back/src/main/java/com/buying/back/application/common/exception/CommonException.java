package com.buying.back.application.common.exception;

import com.buying.back.util.response.ResponseCode;

public class CommonException extends RuntimeException {

  private final ResponseCode responseCode;

  public CommonException(ResponseCode responseCode) {
    this(responseCode, "");
  }

  public CommonException(ResponseCode responseCode, String detailMessage) {
    super(responseCode.getMessage() + " : " + detailMessage);
    this.responseCode = responseCode;
  }

  public ResponseCode getResponseCode() {
    return responseCode;
  }
}
