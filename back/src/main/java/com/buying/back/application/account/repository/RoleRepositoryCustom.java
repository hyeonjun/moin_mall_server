package com.buying.back.application.account.repository;

import com.buying.back.application.account.service.vo.RoleVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RoleRepositoryCustom {

  Page<RoleVO> findAssignedAllByAccountId(Pageable pageable, Long accountId);

}
