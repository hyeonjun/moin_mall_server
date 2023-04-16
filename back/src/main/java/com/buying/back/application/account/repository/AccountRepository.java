package com.buying.back.application.account.repository;

import com.buying.back.application.account.domain.Account;
import com.buying.back.application.account.domain.Brand;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface AccountRepository extends JpaRepository<Account, Long>,
  AccountRepositoryCustom {

  @Query(value = "SELECT A.* from ACCOUNT A WHERE BINARY(A.EMAIL) = :email", nativeQuery = true)
  Optional<Account> findByBinaryEmail(@Param("email") String email);

  Optional<Account> findByEmail(String email);

  List<Account> findAllByBrandAndActivated(Brand brand, Boolean activated);

  @Transactional
  @Modifying(clearAutomatically = true)
  @Query(value = "UPDATE ACCOUNT account SET account.recent_sign_in_date_time = :now WHERE account.account_id = :accountId", nativeQuery = true)
  void updateRecentSignInDateTimeQuery(@Param("accountId") Long accountId, @Param("now") LocalDateTime now);
}
