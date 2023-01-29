package com.buying.back.application.inquiry.code.type;

import static com.buying.back.application.inquiry.code.type.NormalInquiryExchangeRefundType.EXCHANGE_INFO;
import static com.buying.back.application.inquiry.code.type.NormalInquiryExchangeRefundType.REFUND_INFO;
import static com.buying.back.application.inquiry.code.type.NormalInquiryMemberType.MEMBERSHIP_WITHDRAWAL;
import static com.buying.back.application.inquiry.code.type.NormalInquiryMemberType.MEMBER_ETC;
import static com.buying.back.application.inquiry.code.type.NormalInquiryMemberType.MEMBER_INFO;
import static com.buying.back.application.inquiry.code.type.NormalInquiryMemberType.SIGN_UP;
import static com.buying.back.application.inquiry.code.type.NormalInquiryOrderRepaymentType.ORDER_INFO;
import static com.buying.back.application.inquiry.code.type.NormalInquiryOrderRepaymentType.ORDER_REPAYMENT_ETC;
import static com.buying.back.application.inquiry.code.type.NormalInquiryOrderRepaymentType.REPAYMENT_MEANS;
import static com.buying.back.application.inquiry.code.type.NormalInquiryProductType.PRODUCT_ERROR;
import static com.buying.back.application.inquiry.code.type.NormalInquiryProductType.PRODUCT_ETC;
import static com.buying.back.application.inquiry.code.type.NormalInquiryProductType.PRODUCT_INFO;
import static com.buying.back.application.inquiry.code.type.NormalInquiryShippingType.SHIPPING_ETC;
import static com.buying.back.application.inquiry.code.type.NormalInquiryShippingType.SHIPPING_INFO;

import java.util.Arrays;
import java.util.List;

public enum NormalInquiryGroupType implements InquiryParentType {

  MEMBER("회원", Arrays.asList(SIGN_UP, MEMBER_INFO, MEMBERSHIP_WITHDRAWAL, MEMBER_ETC)),

  PRODUCT("상품", Arrays.asList(PRODUCT_INFO, PRODUCT_ERROR, PRODUCT_ETC)),

  ORDER_REPAYMENT("주문/결제", Arrays.asList(
    ORDER_INFO, REPAYMENT_MEANS, ORDER_REPAYMENT_ETC)),

  SHIPPING("배송", Arrays.asList(SHIPPING_INFO, SHIPPING_ETC)),

  EXCHANGE_REFUND("교환/환불", Arrays.asList(EXCHANGE_INFO, REFUND_INFO)),

  ;

  private final String content;
  private final List<InquiryChildType> childList;

  NormalInquiryGroupType(String content, List<InquiryChildType> childList) {
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
