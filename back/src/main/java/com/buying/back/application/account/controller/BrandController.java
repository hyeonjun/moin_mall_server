package com.buying.back.application.account.controller;

import com.buying.back.application.account.controller.dto.CreateAccountDTO;
import com.buying.back.application.account.controller.dto.CreateBrandDTO;
import com.buying.back.application.account.service.BrandService;
import com.buying.back.application.account.service.vo.AccountDefaultVO;
import com.buying.back.util.response.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.buying.back.util.response.CommonResponseCode.SUCCESS;

@RestController
@RequestMapping("/api/v1/auth/brand")
@RequiredArgsConstructor
public class BrandController {

  private final BrandService brandService;

  @PostMapping
  public CommonResponse<AccountDefaultVO> createAccount(@Valid @RequestBody CreateBrandDTO dto) {
    AccountDefaultVO vo = brandService.createAccount(dto);
    return new CommonResponse<>(vo, SUCCESS);
  }

  @PutMapping("/more-information")
  public CommonResponse<AccountDefaultVO> essentialPasswordChange(@Valid @RequestBody CreateAccountDTO dto){
    AccountDefaultVO vo = brandService.essentialPasswordChange(dto);
    return new CommonResponse<>(vo, SUCCESS);
  }
}
