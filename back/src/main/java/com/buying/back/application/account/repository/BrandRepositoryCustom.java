package com.buying.back.application.account.repository;

import com.buying.back.application.account.controller.dto.management.SearchBrandEnterpriseManagementDTO;
import com.buying.back.application.account.service.vo.BrandAccountManagementVO;
import com.buying.back.application.account.service.vo.BrandEnterpriseManagementVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BrandRepositoryCustom {

  Page<BrandEnterpriseManagementVO> findAllEnterprise(Pageable pageable,
    SearchBrandEnterpriseManagementDTO dto);

  Page<BrandAccountManagementVO> findAllAccountByBrandId(Pageable pageable, Long brandId);
}
