package com.buying.back.application.account.controller.dto.management;

import com.buying.back.application.common.dto.PagingDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchBrandEnterpriseManagementDTO extends PagingDTO {

  private Boolean activated;
  private String brandName;
  private String representativeName;
  private String url;
}
