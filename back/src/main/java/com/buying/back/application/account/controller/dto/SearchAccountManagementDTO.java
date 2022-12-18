package com.buying.back.application.account.controller.dto;

import com.buying.back.application.account.code.type.AccountGradeType;
import com.buying.back.application.account.code.type.RoleType;
import com.buying.back.application.common.dto.PagingDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchAccountManagementDTO extends PagingDTO {

  private Boolean activated;
  private RoleType roleType;
  private AccountGradeType accountGradeType;
}
