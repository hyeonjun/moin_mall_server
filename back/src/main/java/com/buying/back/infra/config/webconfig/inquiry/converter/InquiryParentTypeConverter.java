package com.buying.back.infra.config.webconfig.inquiry.converter;

import com.buying.back.application.inquiry.code.exception.InquiryException;
import com.buying.back.application.inquiry.code.exception.InquiryException.InquiryExceptionCode;
import com.buying.back.application.inquiry.code.type.BrandInquiryGroupType;
import com.buying.back.application.inquiry.code.type.InquiryParentType;
import com.buying.back.application.inquiry.code.type.NormalInquiryGroupType;
import org.springframework.core.convert.converter.Converter;

public class InquiryParentTypeConverter implements Converter<String[], InquiryParentType> {

  @Override
  public InquiryParentType convert(String[] source) {
    switch (source[0]) {
      case "Normal":
        return NormalInquiryGroupType.of(source[1]);
      case "Brand":
        return BrandInquiryGroupType.of(source[1]);
      default:
        throw new InquiryException(InquiryExceptionCode.WRONG_INQUIRY_TYPE);
    }
  }
}
