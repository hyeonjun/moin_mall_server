package com.buying.back.application.mock.inquiry;

import com.buying.back.application.inquiry.code.type.InquiryChildType;
import com.buying.back.application.inquiry.code.type.NormalInquiryMemberType;
import com.buying.back.application.inquiry.code.type.NormalInquiryProductType;
import com.buying.back.application.inquiry.code.type.NormalInquiryTypeGroup;
import com.buying.back.application.inquiry.controller.dto.CreateInquiryDTO;

public class InquiryMockDTO {

  public static CreateInquiryDTO createInquiryDTO(boolean isSuccess) {
    CreateInquiryDTO dto = new CreateInquiryDTO();
    dto.setAccountId(1L);
    dto.setInquiryParentType(NormalInquiryTypeGroup.MEMBER);

    InquiryChildType childType = NormalInquiryMemberType.MEMBER_INFO;
    if (!isSuccess) {
      childType = NormalInquiryProductType.PRODUCT_INFO;
    }
    dto.setInquiryChildType(childType);

    dto.setTitle("test inquiry title");
    dto.setContent("test inquiry content");
    return dto;
  }

}
