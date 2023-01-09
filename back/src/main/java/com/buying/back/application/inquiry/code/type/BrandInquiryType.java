package com.buying.back.application.inquiry.code.type;

public enum BrandInquiryType {
  ;


  private final String content;

  BrandInquiryType(String content) {
    this.content = content;
  }

  public String getContent() {
    return content;
  }
}
