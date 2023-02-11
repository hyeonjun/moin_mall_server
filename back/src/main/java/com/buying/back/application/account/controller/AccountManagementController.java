package com.buying.back.application.account.controller;

import static com.buying.back.util.response.CommonResponseCode.SUCCESS;

import com.buying.back.application.account.controller.dto.management.SearchAccountManagementDTO;
import com.buying.back.application.account.controller.dto.management.UpdateActivateAccountDTO;
import com.buying.back.application.account.service.AccountService;
import com.buying.back.application.account.service.vo.NormalAccountManagementVO;
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
  public CommonResponse<Page<NormalAccountManagementVO>> getNormalAccountList(SearchAccountManagementDTO dto) {
    Page<NormalAccountManagementVO> vo = accountService.getNormalAccountList(dto);
    return new CommonResponse<>(vo, SUCCESS);
  }

  @GetMapping("/{account-id}")
  public CommonResponse<NormalAccountManagementVO> getNormalAccountByManagement(
    @PathVariable(value = "account-id") Long accountId) {
    NormalAccountManagementVO vo = accountService.getNormalAccountByManagement(accountId);
    return new CommonResponse<>(vo, SUCCESS);
  }


  @PutMapping("/activate:{account-id}")
  public CommonResponse<NormalAccountManagementVO> activateNormalAccount(
    @PathVariable(value = "account-id") Long accountId,
    @Valid @RequestBody UpdateActivateAccountDTO dto) {
    NormalAccountManagementVO vo = accountService.activateNormalAccount(accountId, dto);
    return new CommonResponse<>(vo, SUCCESS);
  }

  @PutMapping("/{account-id}/reset:password")
  public CommonResponse<NormalAccountManagementVO> resetNormalPassword(
    @PathVariable(value = "account-id") Long accountId) {
    NormalAccountManagementVO resetPassword = accountService.resetNormalPassword(accountId);
    return new CommonResponse<>(resetPassword, SUCCESS);
  }

}
