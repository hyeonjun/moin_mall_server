package com.buying.back.application.inquiry.code;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class InquiryChildData<T extends InquiryChildType> {

  private List<T> childList;

}
