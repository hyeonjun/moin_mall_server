package com.buying.back.application.inquiry.code;

import static com.buying.back.application.inquiry.code.NormalInquiryExchangeRefundType.EXCHANGE_INFO;
import static com.buying.back.application.inquiry.code.NormalInquiryExchangeRefundType.REFUND_INFO;
import static com.buying.back.application.inquiry.code.NormalInquiryMemberType.MEMBERSHIP_WITHDRAWAL;
import static com.buying.back.application.inquiry.code.NormalInquiryMemberType.MEMBER_ETC;
import static com.buying.back.application.inquiry.code.NormalInquiryMemberType.MEMBER_INFO;
import static com.buying.back.application.inquiry.code.NormalInquiryMemberType.SIGN_UP;
import static com.buying.back.application.inquiry.code.NormalInquiryOrderRepaymentType.ORDER_INFO;
import static com.buying.back.application.inquiry.code.NormalInquiryOrderRepaymentType.ORDER_REPAYMENT_ETC;
import static com.buying.back.application.inquiry.code.NormalInquiryOrderRepaymentType.REPAYMENT_MEANS;
import static com.buying.back.application.inquiry.code.NormalInquiryProductType.PRODUCT_ERROR;
import static com.buying.back.application.inquiry.code.NormalInquiryProductType.PRODUCT_ETC;
import static com.buying.back.application.inquiry.code.NormalInquiryProductType.PRODUCT_INFO;
import static com.buying.back.application.inquiry.code.NormalInquiryShippingType.SHIPPING_ETC;
import static com.buying.back.application.inquiry.code.NormalInquiryShippingType.SHIPPING_INFO;

import java.util.Arrays;
import java.util.List;

public enum NormalInquiryTypeGroup implements InquiryParentType {

  MEMBER("회원", new InquiryChildData<>(
    Arrays.asList(SIGN_UP, MEMBER_INFO, MEMBERSHIP_WITHDRAWAL, MEMBER_ETC))),

  PRODUCT("상품", new InquiryChildData<>(
    Arrays.asList(PRODUCT_INFO, PRODUCT_ERROR, PRODUCT_ETC))),

  ORDER_REPAYMENT("주문/결제", new InquiryChildData<>(
    Arrays.asList(ORDER_INFO, REPAYMENT_MEANS, ORDER_REPAYMENT_ETC))),

  SHIPPING("배송", new InquiryChildData<>(
    Arrays.asList(SHIPPING_INFO, SHIPPING_ETC))),

  EXCHANGE_REFUND("교환/환불", new InquiryChildData<>(
    Arrays.asList(EXCHANGE_INFO, REFUND_INFO))),

  ;

  private final String content;
  private final InquiryChildData childList;

  NormalInquiryTypeGroup(String content, InquiryChildData childList) {
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
  public List childList() {
    return childList.getChildList();
  }


}
