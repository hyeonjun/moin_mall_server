package com.buying.back.application.account.repository;

import com.buying.back.application.account.domain.Account;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long>,
  AccountRepositoryCustom {

  Optional<Account> findByEmail(String email);

}
