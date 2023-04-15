package com.buying.back.application.account.helper;

import com.buying.back.application.account.code.exception.AccountException;
import com.buying.back.application.account.code.exception.AccountException.AccountExceptionCode;
import com.buying.back.application.account.code.type.RoleType;
import com.buying.back.application.account.controller.dto.brand.CreateBrandDTO;
import com.buying.back.application.account.controller.dto.management.UpdateActivateAccountDTO;
import com.buying.back.application.account.domain.Account;
import com.buying.back.application.account.domain.Brand;
import com.buying.back.application.account.repository.AccountRepository;
import com.buying.back.application.account.service.AccountService;
import com.buying.back.application.account.service.vo.AccountDefaultVO;
import com.buying.back.util.encryption.PasswordProvider;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BrandAccountHelper {

  private final AccountRepository accountRepository;
  private final PasswordProvider passwordProvider;
  private final AccountService accountService;

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

  @Transactional
  public void updateBrandAccountDeactivate(Brand brand) {
    List<Account> accounts = accountRepository.findAllByBrandAndActivated(brand, Boolean.TRUE)
      .stream().map(account -> {
        account.setActivated(Boolean.FALSE);
        return account;
      }).collect(Collectors.toList());
    accountRepository.saveAll(accounts);
  }

  @Transactional
  public void updateAccountActivate(Account account, Boolean activated) {
    UpdateActivateAccountDTO dto = new UpdateActivateAccountDTO();
    dto.setActivated(activated);

    accountService.updateAccountActivate(account.getId(), dto);
  }

}
