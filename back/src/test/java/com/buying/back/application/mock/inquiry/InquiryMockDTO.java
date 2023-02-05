package com.buying.back.application.mock.inquiry;

import com.buying.back.application.inquiry.code.type.InquiryChildType;
import com.buying.back.application.inquiry.code.type.InquiryParentType;
import com.buying.back.application.inquiry.code.type.NormalInquiryMemberType;
import com.buying.back.application.inquiry.code.type.NormalInquiryProductType;
import com.buying.back.application.inquiry.code.type.NormalInquiryGroupType;
import com.buying.back.application.inquiry.controller.dto.common.CreateInquiryDTO;
import com.buying.back.application.inquiry.controller.dto.normal.SearchInquiryNormalDTO;
import com.buying.back.application.inquiry.controller.dto.common.UpdateInquiryDTO;
import com.buying.back.application.inquiry.controller.dto.management.SearchInquiryManagementDTO;
import java.time.LocalDate;

public class InquiryMockDTO {

  public static CreateInquiryDTO createInquiryDTO(boolean isSuccess) {
    CreateInquiryDTO dto = new CreateInquiryDTO();
    dto.setInquiryParentType(NormalInquiryGroupType.MEMBER);

    InquiryChildType childType = NormalInquiryMemberType.MEMBER_INFO;
    if (!isSuccess) {
      childType = NormalInquiryProductType.PRODUCT_INFO;
    }
    dto.setInquiryChildType(childType);

    dto.setTitle("test inquiry title");
    dto.setContent("test inquiry content");
    return dto;
  }

  public static UpdateInquiryDTO updateInquiryDTO(boolean isSuccess) {
    UpdateInquiryDTO dto = new UpdateInquiryDTO();

    InquiryParentType parentType = NormalInquiryGroupType.MEMBER;
    if (!isSuccess) {
      parentType = NormalInquiryGroupType.EXCHANGE_REFUND;
    }
    dto.setInquiryParentType(parentType);
    dto.setInquiryChildType(NormalInquiryMemberType.MEMBER_ETC);

    dto.setTitle("test inquiry title");
    dto.setContent("test inquiry content");
    return dto;
  }

  public static SearchInquiryNormalDTO searchInquiryNormalDTO() {
    SearchInquiryNormalDTO dto = new SearchInquiryNormalDTO();

    dto.setCreatedDateFrom(LocalDate.now());
    dto.setCreatedDateTo(LocalDate.now());
    dto.setReplied(Boolean.FALSE);
    dto.setInquiryParentType(NormalInquiryGroupType.MEMBER);
    dto.setInquiryChildType(NormalInquiryMemberType.MEMBER_INFO);

    return dto;
  }

  public static SearchInquiryManagementDTO searchInquiryManagementDTO() {
    SearchInquiryManagementDTO dto = new SearchInquiryManagementDTO();

    dto.setCreatedDateFrom(LocalDate.now());
    dto.setCreatedDateTo(LocalDate.now());
    dto.setDeleted(Boolean.FALSE);
    dto.setReplied(Boolean.FALSE);
    dto.setInquiryParentType(NormalInquiryGroupType.MEMBER);
    dto.setInquiryChildType(NormalInquiryMemberType.MEMBER_INFO);
    dto.setAuthorEmail("test");

    return dto;
  }

}
