package com.buying.back.util.validation.validatedto.inquiry;

import com.buying.back.application.inquiry.code.type.InquiryChildType;
import com.buying.back.application.inquiry.code.type.InquiryParentType;
import java.util.Objects;
import javax.validation.constraints.NotBlank;
import org.springframework.validation.Errors;

public class InquiryDTOValidationCondition {

  public static void childTypeNeedParentType(Errors errors,
    InquiryParentType inquiryParentType, InquiryChildType inquiryChildType) {
    if (Objects.nonNull(inquiryChildType) && Objects.isNull(inquiryParentType)) {
      errors.rejectValue("inquiryChildType", NotBlank.class.getSimpleName(),
        "principalRepaymentAccountBranchName is not blank");
    }
  }

  public static void parentChildTypeMatch(Errors errors,
    InquiryParentType inquiryParentType, InquiryChildType inquiryChildType) {
    if (Objects.nonNull(inquiryParentType) && Objects.nonNull(inquiryChildType)) {
      if (!inquiryParentType.childCheck(inquiryChildType)) {
        errors.rejectValue("inquiryParentType", "NotMatches",
          "inquiryChildType and inquiryChildType types do not match");
      }
    }
  }

}
