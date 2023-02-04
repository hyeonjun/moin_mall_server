package com.buying.back.application.inquiry.controller.dto.management;

import com.buying.back.application.common.dto.PagingDTO;
import com.buying.back.application.inquiry.code.type.InquiryChildType;
import com.buying.back.application.inquiry.code.type.InquiryParentType;
import com.buying.back.util.validation.validatedto.ValidateDTO;
import com.buying.back.util.validation.validatedto.inquiry.InquiryDTOValidationCondition;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.validation.Errors;

@Getter
@Setter
public class SearchInquiryManagementDTO extends PagingDTO implements ValidateDTO {

  @DateTimeFormat(iso = ISO.DATE)
  private LocalDate createdDateFrom;

  @DateTimeFormat(iso = ISO.DATE)
  private LocalDate createdDateTo;

  private Boolean deleted;
  private Boolean replied;

  private InquiryParentType inquiryParentType;
  private InquiryChildType inquiryChildType;

  private String authorEmail;

  @Override
  public void validate(Errors e) {
    InquiryDTOValidationCondition.childTypeNeedParentType(
      e, this.inquiryParentType, this.inquiryChildType);
    InquiryDTOValidationCondition.parentChildTypeMatch(
      e, this.inquiryParentType, this.inquiryChildType);
  }
}
