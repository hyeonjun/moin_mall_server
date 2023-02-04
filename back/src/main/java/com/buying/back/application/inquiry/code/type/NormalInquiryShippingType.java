package com.buying.back.application.inquiry.code.type;

import com.buying.back.application.inquiry.code.exception.InquiryException;
import com.buying.back.application.inquiry.code.exception.InquiryException.InquiryExceptionCode;
import java.util.Arrays;

public enum NormalInquiryShippingType implements InquiryChildType {

  SHIPPING_INFO("배송 일반"),
  SHIPPING_ETC("기타"),
  ;

  private final String content;

  NormalInquiryShippingType(String content) {
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

  public static NormalInquiryShippingType of(String value) {
    return Arrays.stream(values())
      .filter(type -> type.getChildCode().equals(value))
      .findFirst()
      .orElseThrow(() -> new InquiryException(InquiryExceptionCode.WRONG_INQUIRY_TYPE));
  }
}
