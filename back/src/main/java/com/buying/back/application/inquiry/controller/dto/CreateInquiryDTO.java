package com.buying.back.application.inquiry.controller.dto;

import com.buying.back.application.inquiry.code.type.InquiryChildType;
import com.buying.back.application.inquiry.code.type.InquiryParentType;
import com.buying.back.util.verify.VerifyLengthUtil;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class CreateInquiryDTO {

  @NotNull
  private InquiryParentType inquiryParentType;
  @NotNull
  private InquiryChildType inquiryChildType;
  @NotBlank
  @Length(min = 1, max = VerifyLengthUtil.MAX_DEFAULT_LENGTH)
  private String title;
  @NotBlank
  @Length(min = 1, max = VerifyLengthUtil.MAX_DESCRIPTION_LENGTH)
  private String content;

}
