package com.buying.back.application.inquiry.service.vo;

import com.buying.back.application.inquiry.service.vo.defaultvo.InquiryDefaultVO;
import com.querydsl.core.annotations.QueryProjection;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class InquiryManagementVO extends InquiryDefaultVO {

  private boolean isAnswered;
  private boolean isDeleted;

  @QueryProjection
  public InquiryManagementVO(Long authorId, String authorName, Long inquiryId, LocalDateTime createDateTime,
    LocalDateTime updateDateTime, String title, String inquiryParentType, boolean isAnswered, boolean isDeleted) {
    this.authorId = authorId;
    this.authorName = authorName;
    this.inquiryId = inquiryId;
    this.createDateTime = createDateTime;
    this.updateDateTime = updateDateTime;
    this.title = title;
    this.inquiryParentType = inquiryParentType;
    this.isAnswered = isAnswered;
    this.isDeleted = isDeleted;
  }
}
