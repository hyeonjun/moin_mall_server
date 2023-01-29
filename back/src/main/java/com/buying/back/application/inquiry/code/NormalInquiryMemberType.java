package com.buying.back.application.inquiry.code;

public enum NormalInquiryMemberType implements InquiryChildType {

    SIGN_UP("회원 가입"),
    MEMBER_INFO("회원 정보"),
    MEMBERSHIP_WITHDRAWAL("회원 탈퇴"),
    MEMBER_ETC("기타"),
    ;

  private final String content;

  NormalInquiryMemberType(String content) {
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
