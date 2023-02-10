package com.buying.back.application.account.repository;

import com.buying.back.application.account.controller.dto.management.SearchAccountManagementDTO;
import com.buying.back.application.account.service.vo.AccountManagementVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AccountRepositoryCustom {

  Page<AccountManagementVO> findAll(Pageable pageable, SearchAccountManagementDTO dto);
}
