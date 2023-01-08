package com.buying.back.application.inquiry.code;

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
}
