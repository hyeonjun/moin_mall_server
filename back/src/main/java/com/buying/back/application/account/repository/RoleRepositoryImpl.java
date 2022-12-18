package com.buying.back.application.account.repository;

import static com.buying.back.application.account.domain.QAccount.account;
import static com.buying.back.application.account.domain.QRole.role;

import com.buying.back.application.account.domain.QRole;
import com.buying.back.application.account.domain.Role;
import com.buying.back.application.account.service.vo.QRoleVO;
import com.buying.back.application.account.service.vo.RoleVO;
import com.buying.back.util.querydsl.CustomQuerydslRepositorySupport;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class RoleRepositoryImpl extends CustomQuerydslRepositorySupport
  implements RoleRepositoryCustom {


  public RoleRepositoryImpl() {
    super(Role.class);
  }

  @Override
  public Page<RoleVO> findAssignedAllByAccountId(Pageable pageable, Long accountId) {
    JPAQuery<RoleVO> query = select(getRoleVO())
      .from(role)
      .where(role.account.accountId.eq(accountId))
      .orderBy(role.roleId.asc());

    JPAQuery<Long> countQuery = select(role.count())
      .from(role)
      .where(role.account.accountId.eq(accountId));

    return applyPagination(pageable, query, countQuery);
  }

  private QRoleVO getRoleVO() {
    return new QRoleVO(
      role.roleId,
      role.roleName
    );
  }


}
