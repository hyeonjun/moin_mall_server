package com.buying.back.application.inquiry.controller.dto.management;

import com.buying.back.application.inquiry.controller.dto.common.SearchInquiryDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchInquiryManagementDTO extends SearchInquiryDTO {

  private Boolean replied;

  private Boolean deleted;

  private String authorEmail;

}
