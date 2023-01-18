package com.buying.back.application.inquiry.service.vo;

import com.buying.back.application.account.domain.Account;
import com.buying.back.application.inquiry.domain.Inquiry;
import com.buying.back.application.inquiry.service.vo.defaultvo.InquiryDefaultVO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class InquiryDetailVO extends InquiryDefaultVO {

  private String content;
  private String answer;
  private String inquiryChildType;

  public InquiryDetailVO(Account author, Inquiry inquiry) {
    super(author, inquiry);
  }

  // TODO: 2023/01/08 사진 VO
  public static InquiryDetailVO valueOf(Account author, Inquiry inquiry) {
    InquiryDetailVO vo = new InquiryDetailVO(author, inquiry);
    vo.setContent(inquiry.getContent());
    vo.setAnswer(inquiry.getAnswer());
    vo.setInquiryChildType(inquiry.getInquiryChildType());
    return vo;
  }

}
