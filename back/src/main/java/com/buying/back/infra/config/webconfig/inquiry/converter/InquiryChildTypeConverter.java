package com.buying.back.infra.config.webconfig.inquiry.converter;

import com.buying.back.application.inquiry.code.exception.InquiryException;
import com.buying.back.application.inquiry.code.exception.InquiryException.InquiryExceptionCode;
import com.buying.back.application.inquiry.code.type.InquiryChildType;
import com.buying.back.application.inquiry.code.type.NormalInquiryExchangeRefundType;
import com.buying.back.application.inquiry.code.type.NormalInquiryMemberType;
import com.buying.back.application.inquiry.code.type.NormalInquiryOrderRepaymentType;
import com.buying.back.application.inquiry.code.type.NormalInquiryProductType;
import com.buying.back.application.inquiry.code.type.NormalInquiryShippingType;
import org.springframework.core.convert.converter.Converter;

public class InquiryChildTypeConverter implements Converter<String[], InquiryChildType> {

  @Override
  public InquiryChildType convert(String[] source) {
    switch (source[0]) {
      case "Member":
        return NormalInquiryMemberType.of(source[1]);
      case "Product":
        return NormalInquiryProductType.of(source[1]);
      case "OrderRepayment":
        return NormalInquiryOrderRepaymentType.of(source[1]);
      case "Shipping":
        return NormalInquiryShippingType.of(source[1]);
      case "ExchangeRefund":
        return NormalInquiryExchangeRefundType.of(source[1]);
      default:
        throw new InquiryException(InquiryExceptionCode.WRONG_INQUIRY_TYPE);
    }
  }
}
