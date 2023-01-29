package com.buying.back.application.inquiry.code.type;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

@JsonTypeInfo(use = Id.NAME)
@JsonSubTypes({
  @JsonSubTypes.Type(value = NormalInquiryMemberType.class, name = "Member"),
  @JsonSubTypes.Type(value = NormalInquiryProductType.class, name = "Product"),
  @JsonSubTypes.Type(value = NormalInquiryOrderRepaymentType.class, name = "OrderRepayment"),
  @JsonSubTypes.Type(value = NormalInquiryShippingType.class, name = "Shipping"),
  @JsonSubTypes.Type(value = NormalInquiryExchangeRefundType.class, name = "ExchangeRefund"),
})
public interface InquiryChildType {

  String getChildCode();
  String getChildContent();

}
