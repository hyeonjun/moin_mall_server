package com.buying.back.application.account.controller;

import static com.buying.back.util.response.CommonResponseCode.SUCCESS;

import com.buying.back.application.account.controller.dto.management.SearchBrandEnterpriseManagementDTO;
import com.buying.back.application.account.service.BrandService;
import com.buying.back.application.account.service.vo.BrandAccountManagementVO;
import com.buying.back.application.account.service.vo.BrandEnterpriseManagementVO;
import com.buying.back.application.common.dto.PagingDTO;
import com.buying.back.util.response.CommonResponse;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/sys/brands")
@RequiredArgsConstructor
public class BrandManagementController {

  private final BrandService brandService;

  @GetMapping
  public CommonResponse<Page<BrandEnterpriseManagementVO>> getBrandEnterpriseList(
    @Valid SearchBrandEnterpriseManagementDTO dto) {
    Page<BrandEnterpriseManagementVO> list = brandService.getBrandEnterpriseList(dto);
    return new CommonResponse<>(list, SUCCESS);
  }

  @GetMapping("/{brand-id}/accounts")
  public CommonResponse<Page<BrandAccountManagementVO>> getBrandAccountList(
    @PathVariable(value = "brand-id") Long brandId,
    @Valid PagingDTO dto) {
    Page<BrandAccountManagementVO> list = brandService.getBrandAccountList(brandId, dto);
    return new CommonResponse<>(list, SUCCESS);
  }

}
