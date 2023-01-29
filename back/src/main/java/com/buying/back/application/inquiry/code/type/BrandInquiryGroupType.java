package com.buying.back.application.inquiry.code.type;

import java.util.List;

public enum BrandInquiryGroupType implements InquiryParentType{
  ;

  private final String content;
  private final List<InquiryChildType> childList;

  BrandInquiryGroupType(String content, List<InquiryChildType> childList) {
    this.content = content;
    this.childList = childList;
  }

  @Override
  public String getParentCode() {
    return name();
  }

  @Override
  public String getParentContent() {
    return content;
  }

  @Override
  public List<InquiryChildType> getChildList() {
    return childList;
  }

  @Override
  public boolean childCheck(InquiryChildType childType) {
    return this.getChildList().stream()
      .anyMatch(child -> child == childType);
  }
}
