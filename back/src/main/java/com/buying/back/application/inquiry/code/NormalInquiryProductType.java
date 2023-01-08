package com.buying.back.application.inquiry.code;

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
}
