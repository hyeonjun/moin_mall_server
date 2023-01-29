package com.buying.back.application.inquiry.code.type;

import java.util.List;

public interface InquiryParentType {

  String getParentCode();
  String getParentContent();
  List<InquiryChildType> getChildList();

  boolean childCheck(InquiryChildType childType);

}
