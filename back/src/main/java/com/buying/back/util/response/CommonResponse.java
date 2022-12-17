package com.buying.back.util.response;

import com.buying.back.application.common.exception.CommonException;

public class CommonResponse<T> {

  private final T data;
  private final String code;
  private final String message;

  public CommonResponse(ResponseCode responseCode) {
    this(null, responseCode);
  }

  public CommonResponse(CommonException e) {
    this(null, e.getResponseCode().getCode(), e.getMessage());
  }

  public CommonResponse(T data, ResponseCode responseCode) {
    this(data, responseCode.getCode(), responseCode.getMessage());
  }

  public CommonResponse(T data, String code, String message) {
    this.data = data;
    this.code = code;
    this.message = message;
  }

  public T getData() {
    return data;
  }

  public String getCode() {
    return code;
  }

  public String getMessage() {
    return message;
  }
}
