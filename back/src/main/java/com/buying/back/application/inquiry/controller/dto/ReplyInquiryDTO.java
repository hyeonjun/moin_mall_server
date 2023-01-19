package com.buying.back.application.inquiry.controller.dto;

import com.buying.back.util.verify.VerifyLengthUtil;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class ReplyInquiryDTO {

  @NotNull
  private Long inquiryId;
  @NotBlank
  @Length(min = 1, max = VerifyLengthUtil.MAX_DESCRIPTION_LENGTH)
  private String answer;
}
