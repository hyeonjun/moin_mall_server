package com.buying.back.application.account.controller;

import static com.buying.back.util.response.CommonResponseCode.SUCCESS;

import com.buying.back.application.account.controller.dto.CreateAccountDTO;
import com.buying.back.application.account.service.AccountService;
import com.buying.back.application.account.service.vo.AccountDefaultVO;
import com.buying.back.util.response.CommonResponse;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth/accounts")
@RequiredArgsConstructor
public class AuthController {

  private final AccountService accountService;

  @PostMapping
  public CommonResponse<AccountDefaultVO> createAccount(@Valid @RequestBody CreateAccountDTO dto) {
    AccountDefaultVO vo = accountService.createAccount(dto);
    return new CommonResponse<>(vo, SUCCESS);
  }
}
