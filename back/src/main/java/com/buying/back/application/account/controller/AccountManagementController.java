package com.buying.back.application.account.controller;

import static com.buying.back.util.response.CommonResponseCode.SUCCESS;

import com.buying.back.application.account.controller.dto.SearchAccountManagementDTO;
import com.buying.back.application.account.controller.dto.UpdateActivateAccountDTO;
import com.buying.back.application.account.service.AccountService;
import com.buying.back.application.account.service.vo.AccountManagementVO;
import com.buying.back.util.response.CommonResponse;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/sys/accounts")
@RequiredArgsConstructor
public class AccountManagementController {

  private final AccountService accountService;

  @GetMapping
  public CommonResponse<Page<AccountManagementVO>> getAccountList(SearchAccountManagementDTO dto) {
    Page<AccountManagementVO> vo = accountService.getAccountList(dto);
    return new CommonResponse<>(vo, SUCCESS);
  }

  @PutMapping("/activate:{account-id}")
  public CommonResponse<AccountManagementVO> activateAccount(
    @PathVariable(value = "account-id") Long accountId,
    @Valid @RequestBody UpdateActivateAccountDTO dto) {
    AccountManagementVO vo = accountService.activateAccount(accountId, dto);
    return new CommonResponse<>(vo, SUCCESS);
  }

}
