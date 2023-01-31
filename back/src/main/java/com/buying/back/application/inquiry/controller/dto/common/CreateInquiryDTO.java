package com.buying.back.application.inquiry.controller.dto.common;

import com.buying.back.application.inquiry.code.type.InquiryChildType;
import com.buying.back.application.inquiry.code.type.InquiryParentType;
import com.buying.back.util.validation.validatedto.ValidateDTO;
import com.buying.back.util.validation.validatedto.inquiry.InquiryDTOValidationCondition;
import com.buying.back.util.verify.VerifyLengthUtil;
import java.net.URL;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.Errors;

@Getter
@Setter
public class CreateInquiryDTO implements ValidateDTO {

  private URL url;

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

  @Override
  public void validate(Errors e) {
    InquiryDTOValidationCondition.parentChildTypeMatch(
      e, this.inquiryParentType, this.getInquiryChildType());
  }
}
