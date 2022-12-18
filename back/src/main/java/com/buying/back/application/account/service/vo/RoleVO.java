package com.buying.back.application.account.service.vo;

import com.buying.back.application.account.domain.Role;
import com.querydsl.core.annotations.QueryProjection;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = "roleId")
public class RoleVO implements GrantedAuthority {

  private Long roleId;
  private String roleName;
  private String authority;

  public RoleVO(Role role) {
    this.roleId = role.getRoleId();
    this.roleName = role.getRoleName();
    this.authority = role.getRoleName();
  }

  @QueryProjection
  public RoleVO(Long roleId, String roleName) {
    this.roleId = roleId;
    this.roleName = roleName;
    this.authority = roleName;
  }
}
