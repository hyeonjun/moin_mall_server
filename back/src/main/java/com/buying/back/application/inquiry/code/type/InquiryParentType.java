package com.buying.back.application.inquiry.code.type;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import java.util.List;

@JsonTypeInfo(use = Id.NAME)
@JsonSubTypes({
  @JsonSubTypes.Type(value = NormalInquiryGroupType.class, name = "Normal"),
  @JsonSubTypes.Type(value = BrandInquiryGroupType.class, name = "Brand")
})
public interface InquiryParentType {

  String getParentCode();
  String getParentContent();
  List<InquiryChildType> getChildList();

  boolean childCheck(InquiryChildType childType);

}
