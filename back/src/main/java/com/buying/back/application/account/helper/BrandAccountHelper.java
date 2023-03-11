package com.buying.back.application.account.helper;

import com.buying.back.application.account.code.exception.AccountException;
import com.buying.back.application.account.code.exception.AccountException.AccountExceptionCode;
import com.buying.back.application.account.code.type.RoleType;
import com.buying.back.application.account.controller.dto.brand.CreateBrandDTO;
import com.buying.back.application.account.domain.Account;
import com.buying.back.application.account.domain.Brand;
import com.buying.back.application.account.repository.AccountRepository;
import com.buying.back.util.encryption.PasswordProvider;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BrandAccountHelper {

  private final AccountRepository accountRepository;
  private final PasswordProvider passwordProvider;

  @Transactional
  public Account createBrandAdminAccount(CreateBrandDTO dto, Brand brand, RoleType roleType) {
    Account account = accountRepository.findByEmail(dto.getAccountEmail())
      .orElse(null);

    if (Objects.nonNull(account)) {
      throw new AccountException(AccountExceptionCode.ALREADY_EXIST_ACCOUNT);
    }

    dto.setAccountPassword(passwordProvider.encode(dto.getAccountPassword()));
    account = Account.initBrandAccount()
      .dto(dto)
      .brand(brand)
      .roleType(roleType)
      .build();

    accountRepository.save(account);
    return account;
  }

}
