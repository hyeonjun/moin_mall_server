package com.buying.back.application.account.controller;

import static com.buying.back.util.response.CommonResponseCode.SUCCESS;

import com.buying.back.application.account.controller.dto.account.CreateAccountDTO;
import com.buying.back.application.account.controller.dto.account.UpdateAccountPasswordDTO;
import com.buying.back.application.account.controller.dto.account.UpdateAuthPasswordDTO;
import com.buying.back.application.account.controller.dto.brand.CreateBrandAdminAccountDTO;
import com.buying.back.application.account.controller.dto.brand.CreateBrandCrewAccountDTO;
import com.buying.back.application.account.service.AccountService;
import com.buying.back.application.account.service.BrandService;
import com.buying.back.application.account.service.vo.AccountDefaultVO;
import com.buying.back.application.account.service.vo.BrandAccountDetailVO;
import com.buying.back.application.account.service.vo.BrandDetailVO;
import com.buying.back.util.response.CommonResponse;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
  public CommonResponse<BrandAccountDetailVO> createBrandAccount(@Valid @RequestBody CreateBrandCrewAccountDTO dto) {
    BrandAccountDetailVO vo = brandService.createBrandCrewAccount(dto);
    return new CommonResponse<>(vo, SUCCESS);
  }

  @PostMapping("/normal")
  public CommonResponse<AccountDefaultVO> createNormalAccount(@Valid @RequestBody CreateAccountDTO dto) {
    AccountDefaultVO vo = accountService.createNormalAccount(dto);
    return new CommonResponse<>(vo, SUCCESS);
  }

  @PutMapping("/normal/update:password")
  public CommonResponse<AccountDefaultVO> updateNormalAccountPassword(
    @Valid @RequestBody UpdateAuthPasswordDTO dto) {
    AccountDefaultVO vo = accountService.updateAuthPassword(dto);
    return new CommonResponse<>(vo, SUCCESS);
  }
}
