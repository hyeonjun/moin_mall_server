package com.buying.back.application.account.repository;

import com.buying.back.application.account.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long>, RoleRepositoryCustom {

}
