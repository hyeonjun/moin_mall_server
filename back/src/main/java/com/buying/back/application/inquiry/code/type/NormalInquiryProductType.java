package com.buying.back.application.inquiry.code.type;

import com.buying.back.application.inquiry.code.exception.InquiryException;
import com.buying.back.application.inquiry.code.exception.InquiryException.InquiryExceptionCode;
import java.util.Arrays;

public enum NormalInquiryProductType implements InquiryChildType {

  PRODUCT_INFO("상품 정보"),
  PRODUCT_ERROR("상품 불량"),
  PRODUCT_ETC("기타"),
  ;

  private final String content;

  NormalInquiryProductType(String content) {
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

  public static NormalInquiryProductType of(String value) {
    return Arrays.stream(values())
      .filter(type -> type.getChildCode().equals(value))
      .findFirst()
      .orElseThrow(() -> new InquiryException(InquiryExceptionCode.WRONG_INQUIRY_TYPE));
  }
}
