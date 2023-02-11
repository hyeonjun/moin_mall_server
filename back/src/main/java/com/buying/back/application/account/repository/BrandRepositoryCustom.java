package com.buying.back.application.account.repository;

import com.buying.back.application.account.controller.dto.management.SearchBrandEnterpriseManagementDTO;
import com.buying.back.application.account.service.vo.BrandEnterpriseListVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BrandRepositoryCustom {

  Page<BrandEnterpriseListVO> findAllEnterprise(Pageable pageable,
    SearchBrandEnterpriseManagementDTO dto);
}
