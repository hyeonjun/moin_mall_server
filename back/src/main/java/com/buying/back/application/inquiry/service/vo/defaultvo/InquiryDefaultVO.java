package com.buying.back.application.inquiry.service.vo.defaultvo;

import com.buying.back.application.account.domain.Account;
import com.buying.back.application.inquiry.domain.Inquiry;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class InquiryDefaultVO {

  protected Long authorId;
  protected String authorName;
  protected Long inquiryId;
  protected LocalDateTime createDateTime;
  protected LocalDateTime updateDateTime;
  protected String title;
  protected String inquiryParentType;

  public InquiryDefaultVO(Account author, Inquiry inquiry) {
    this.authorId = author.getId();
    this.authorName = author.getName();
    this.inquiryId = inquiry.getId();
    this.createDateTime = inquiry.getCreatedDateTime();
    this.updateDateTime = inquiry.getUpdatedDateTime();
    this.title = inquiry.getTitle();
    this.inquiryParentType = inquiry.getInquiryParentType();
  }
}
