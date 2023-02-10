package com.buying.back.application.account.controller;

import static com.buying.back.util.response.CommonResponseCode.SUCCESS;

import com.buying.back.application.account.controller.dto.account.CreateAccountDTO;
import com.buying.back.application.account.controller.dto.brand.CreateBrandAdminAccountDTO;
import com.buying.back.application.account.controller.dto.brand.CreateBrandCrewAccountDTO;
import com.buying.back.application.account.service.AccountService;
import com.buying.back.application.account.service.BrandService;
import com.buying.back.application.account.service.vo.AccountDefaultVO;
import com.buying.back.application.account.service.vo.BrandAccountVO;
import com.buying.back.application.account.service.vo.BrandDetailVO;
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

  private final BrandService brandService;

  @PostMapping("/brands/create:enterprise")
  public CommonResponse<BrandDetailVO> createBrandEnterprise(@Valid @RequestBody CreateBrandAdminAccountDTO dto) {
    BrandDetailVO vo = brandService.createBrandEnterprise(dto);
    return new CommonResponse<>(vo, SUCCESS);
  }

  @PostMapping("/brands/create:account")
  public CommonResponse<BrandAccountVO> createBrandAccount(@Valid @RequestBody CreateBrandCrewAccountDTO dto) {
    BrandAccountVO vo = brandService.createBrandCrewAccount(dto);
    return new CommonResponse<>(vo, SUCCESS);
  }

  @PostMapping("/normal")
  public CommonResponse<AccountDefaultVO> createNormalAccount(@Valid @RequestBody CreateAccountDTO dto) {
    AccountDefaultVO vo = accountService.createNormalAccount(dto);
    return new CommonResponse<>(vo, SUCCESS);
  }
}
