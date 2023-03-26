package com.buying.back.application.account.controller;

import static com.buying.back.util.response.CommonResponseCode.SUCCESS;

import com.buying.back.application.account.controller.dto.account.UpdateAccountDTO;
import com.buying.back.application.account.controller.dto.brand.UpdateBrandInfoDTO;
import com.buying.back.application.account.service.BrandService;
import com.buying.back.application.account.service.vo.BrandAccountDetailVO;
import com.buying.back.application.account.service.vo.BrandDetailVO;
import com.buying.back.infra.config.security.loginuser.LoginUser;
import com.buying.back.util.response.CommonResponse;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/brd/brands")
@RequiredArgsConstructor
public class BrandController {

  private final BrandService brandService;

  @GetMapping("/accounts/my-information")
  public CommonResponse<BrandAccountDetailVO> getBrandAccountMyInformation(
    @AuthenticationPrincipal LoginUser loginUser) {
    BrandAccountDetailVO vo = brandService.getBrandAccountMyInformation(
      loginUser.getBrandId(), loginUser.getAccountId());
    return new CommonResponse<>(vo, SUCCESS);
  }

  @PutMapping("/accounts")
  public CommonResponse<BrandAccountDetailVO> updateBrandAccountMyInformation(
    @AuthenticationPrincipal LoginUser loginUser,
    @RequestBody @Valid UpdateAccountDTO dto) {
    BrandAccountDetailVO vo = brandService.updateBrandAccountMyInformation(
      loginUser.getBrandId(), loginUser.getAccountId(), dto);
    return new CommonResponse<>(vo, SUCCESS);
  }

  @PutMapping
  public CommonResponse<BrandDetailVO> updateBrandEnterpriseInfo(
    @AuthenticationPrincipal LoginUser loginUser,
    @RequestBody @Valid UpdateBrandInfoDTO dto) {
    BrandDetailVO vo = brandService
      .updateBrandEnterpriseInfo(loginUser.getBrandId(), loginUser.getAccountId(), dto);
    return new CommonResponse<>(vo, SUCCESS);
  }
}
