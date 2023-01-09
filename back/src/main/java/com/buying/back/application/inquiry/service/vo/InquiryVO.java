package com.buying.back.application.inquiry.service.vo;

import com.buying.back.application.account.domain.Account;
import com.buying.back.application.inquiry.domain.Inquiry;
import com.querydsl.core.annotations.QueryProjection;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class InquiryVO {

  private Long authorId;
  private String authorName;
  private Long inquiryId;
  private LocalDateTime createDateTime;
  private LocalDateTime updateDateTime;
  private String title;
  private String content;
  private String inquiryParentType;
  private String inquiryChildType;

  // TODO: 2023/01/08 사진, 답변 관련 VO

  public static InquiryVO valueOf(Account author, Inquiry inquiry) {
    InquiryVO vo = new InquiryVO();
    vo.setAuthorId(author.getId());
    vo.setAuthorName(author.getName());

    vo.setInquiryId(inquiry.getId());
    vo.setCreateDateTime(inquiry.getCreatedDateTime());
    vo.setUpdateDateTime(inquiry.getUpdatedDateTime());
    vo.setTitle(inquiry.getTitle());
    vo.setContent(inquiry.getContent());
    vo.setInquiryParentType(inquiry.getInquiryParentType());
    vo.setInquiryChildType(inquiry.getInquiryChildType());
    return vo;
  }

  @QueryProjection
  public InquiryVO(Long authorId, String authorName, Long inquiryId, LocalDateTime createDateTime,
    LocalDateTime updateDateTime, String title, String content, String inquiryParentType,
    String inquiryChildType) {
    this.authorId = authorId;
    this.authorName = authorName;
    this.inquiryId = inquiryId;
    this.createDateTime = createDateTime;
    this.updateDateTime = updateDateTime;
    this.title = title;
    this.content = content;
    this.inquiryParentType = inquiryParentType;
    this.inquiryChildType = inquiryChildType;
  }
}
