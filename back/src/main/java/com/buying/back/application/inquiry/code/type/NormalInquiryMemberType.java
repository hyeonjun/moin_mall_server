package com.buying.back.application.inquiry.code.type;

import com.buying.back.application.inquiry.code.exception.InquiryException;
import com.buying.back.application.inquiry.code.exception.InquiryException.InquiryExceptionCode;
import java.util.Arrays;

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

  public static NormalInquiryMemberType of(String value) {
    return Arrays.stream(values())
      .filter(type -> type.getChildCode().equals(value))
      .findFirst()
      .orElseThrow(() -> new InquiryException(InquiryExceptionCode.WRONG_INQUIRY_TYPE));
  }
}
