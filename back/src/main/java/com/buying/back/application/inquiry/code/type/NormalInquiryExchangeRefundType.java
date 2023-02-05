package com.buying.back.application.inquiry.code.type;

import com.buying.back.application.inquiry.code.exception.InquiryException;
import com.buying.back.application.inquiry.code.exception.InquiryException.InquiryExceptionCode;
import java.util.Arrays;

public enum NormalInquiryExchangeRefundType implements InquiryChildType {

  EXCHANGE_INFO("교환"),
  REFUND_INFO("환불"),
  ;

  private final String content;

  NormalInquiryExchangeRefundType(String content) {
    this.content = content;
  }

  @Override
  public String getChildCode() {
    return name();
  }

  @Override
  public String getChildContent() {
    return content;
  }

  public static NormalInquiryExchangeRefundType of(String value) {
    return Arrays.stream(values())
      .filter(type -> type.getChildCode().equals(value))
      .findFirst()
      .orElseThrow(() -> new InquiryException(InquiryExceptionCode.WRONG_INQUIRY_TYPE));
  }
}
