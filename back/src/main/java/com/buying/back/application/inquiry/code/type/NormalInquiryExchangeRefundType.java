package com.buying.back.application.inquiry.code.type;

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
}
