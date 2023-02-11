package com.buying.back.application.account.controller;

import static com.buying.back.util.response.CommonResponseCode.SUCCESS;

import com.buying.back.application.account.controller.dto.management.SearchBrandEnterpriseManagementDTO;
import com.buying.back.application.account.service.BrandService;
import com.buying.back.application.account.service.vo.BrandEnterpriseListVO;
import com.buying.back.util.response.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/sys/brands")
@RequiredArgsConstructor
public class BrandManagementController {

  private final BrandService brandService;

  @GetMapping
  public CommonResponse<Page<BrandEnterpriseListVO>> getBrandEnterpriseList(
    SearchBrandEnterpriseManagementDTO dto) {
    Page<BrandEnterpriseListVO> list = brandService.getBrandEnterpriseList(dto);
    return new CommonResponse<>(list, SUCCESS);
  }

}
