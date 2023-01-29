package com.buying.back.application.inquiry.code.type;

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
}
