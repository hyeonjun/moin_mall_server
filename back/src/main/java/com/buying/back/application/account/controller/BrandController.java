package com.buying.back.application.account.controller;

import com.buying.back.application.account.controller.dto.CreateAccountDTO;
import com.buying.back.application.account.controller.dto.CreateBrandDTO;
import com.buying.back.application.account.controller.dto.UpdatePasswordBrandAccountDTO;
import com.buying.back.application.account.service.BrandService;
import com.buying.back.application.account.service.vo.AccountDefaultVO;
import com.buying.back.infra.config.security.loginuser.LoginUser;
import com.buying.back.util.response.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.buying.back.util.response.CommonResponseCode.SUCCESS;

@RestController
@RequestMapping("/api/v1/brands")
@RequiredArgsConstructor
public class BrandController {

  private final BrandService brandService;


  @PutMapping("/account/password")
  public CommonResponse<AccountDefaultVO> essentialUpdatePassword(@AuthenticationPrincipal LoginUser loginUser, UpdatePasswordBrandAccountDTO dto){
    AccountDefaultVO vo = brandService.essentialUpdatePassword(loginUser.getAccountId(), dto);
    return new CommonResponse<>(vo, SUCCESS);
  }
}
