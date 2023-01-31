package com.buying.back.application.inquiry.controller.dto.management;

import com.buying.back.application.common.dto.PagingDTO;
import com.buying.back.application.inquiry.code.type.InquiryChildType;
import com.buying.back.application.inquiry.code.type.InquiryParentType;
import com.buying.back.application.inquiry.code.type.NormalInquiryGroupType;
import com.buying.back.util.validation.validatedto.ValidateDTO;
import com.buying.back.util.validation.validatedto.inquiry.InquiryDTOValidationCondition;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.core.serializer.Deserializer;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.validation.Errors;

@Getter
@NoArgsConstructor
//@Setter
public class SearchInquiryManagementDTO extends PagingDTO implements ValidateDTO, Serializable {

  private Long inquiryId;

  @DateTimeFormat(iso = ISO.DATE)
  private LocalDate createdDateFrom;

  @DateTimeFormat(iso = ISO.DATE)
  private LocalDate createdDateTo;

  private Boolean deleted;
  private Boolean replied;

  private InquiryParentType inquiryParentType;
  private InquiryChildType inquiryChildType;

  private String authorEmail;

  public void setInquiryId(Long inquiryId) {
    this.inquiryId = inquiryId;
  }

  public void setCreatedDateFrom(LocalDate createdDateFrom) {
    this.createdDateFrom = createdDateFrom;
  }

  public void setCreatedDateTo(LocalDate createdDateTo) {
    this.createdDateTo = createdDateTo;
  }

  public void setDeleted(Boolean deleted) {
    this.deleted = deleted;
  }

  public void setReplied(Boolean replied) {
    this.replied = replied;
  }

  public void setInquiryParentType(InquiryParentType inquiryParentType) {
    if (inquiryParentType instanceof NormalInquiryGroupType) {
      System.out.println(inquiryParentType.getChildList());
    }
    this.inquiryParentType = inquiryParentType;
    System.out.println(this.inquiryParentType.getParentCode());
  }

  public void setInquiryChildType(InquiryChildType inquiryChildType) {
    this.inquiryChildType = inquiryChildType;
  }

  public void setAuthorEmail(String authorEmail) {
    this.authorEmail = authorEmail;
  }

  @Override
  public void validate(Errors e) {
    InquiryDTOValidationCondition.childTypeNeedParentType(
      e, this.inquiryParentType, this.inquiryChildType);
  }
}
