package com.buying.back.application.account.helper;

import com.buying.back.application.account.repository.RoleRepository;
import com.buying.back.application.account.service.vo.RoleVO;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountHelper {

  private final RoleRepository roleRepository;

  public List<RoleVO> getAssignedRoleByAccount(Long accountId, Pageable pageable) {
    return roleRepository.findAssignedAllByAccountId(pageable, accountId)
      .stream().collect(Collectors.toList());
  }

}
