package com.buying.back.application.inquiry.code.type;

import com.buying.back.application.inquiry.code.exception.InquiryException;
import com.buying.back.application.inquiry.code.exception.InquiryException.InquiryExceptionCode;
import java.util.Arrays;

public enum NormalInquiryOrderRepaymentType implements InquiryChildType {

  ORDER_INFO("주문"),
  REPAYMENT_MEANS("결제 방법"),
  ORDER_REPAYMENT_ETC("기타"),
  ;

  private final String content;

  NormalInquiryOrderRepaymentType(String content) {
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

  public static NormalInquiryOrderRepaymentType of(String value) {
    return Arrays.stream(values())
      .filter(type -> type.getChildCode().equals(value))
      .findFirst()
      .orElseThrow(() -> new InquiryException(InquiryExceptionCode.WRONG_INQUIRY_TYPE));
  }
}
