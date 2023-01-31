package com.buying.back.application.inquiry.repository.param;

import com.buying.back.application.inquiry.code.type.InquiryChildType;
import com.buying.back.application.inquiry.code.type.InquiryParentType;
import com.buying.back.application.inquiry.controller.dto.management.SearchInquiryManagementDTO;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchInquiryListParam {

  private Long inquiryId;

  private LocalDate createdDateFrom;
  private LocalDate createdDateTo;

  private Boolean deleted;
  private Boolean replied;

  private InquiryParentType inquiryParentType;
  private InquiryChildType inquiryChildType;

  private Long authorId;
  private String authorEmail;

  public static SearchInquiryListParam valueOf(SearchInquiryManagementDTO dto) {
    SearchInquiryListParam param = new SearchInquiryListParam();
    param.setInquiryId(dto.getInquiryId());
    param.setCreatedDateFrom(dto.getCreatedDateFrom());
    param.setCreatedDateTo(dto.getCreatedDateTo());
    param.setDeleted(dto.getDeleted());
    param.setReplied(dto.getReplied());
    param.setInquiryParentType(dto.getInquiryParentType());
    param.setInquiryChildType(dto.getInquiryChildType());
    param.setAuthorEmail(dto.getAuthorEmail());
    return param;
  }

}
