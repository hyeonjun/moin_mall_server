package com.buying.back.application.account.service;

import com.buying.back.application.account.code.exception.AccountException;
import com.buying.back.application.account.code.exception.AccountException.AccountExceptionCode;
import com.buying.back.application.account.controller.dto.account.CreateAccountDTO;
import com.buying.back.application.account.controller.dto.account.UpdateAccountDTO;
import com.buying.back.application.account.controller.dto.account.UpdateAccountPasswordDTO;
import com.buying.back.application.account.controller.dto.account.UpdateAuthPasswordDTO;
import com.buying.back.application.account.controller.dto.management.SearchAccountManagementDTO;
import com.buying.back.application.account.controller.dto.management.UpdateActivateAccountDTO;
import com.buying.back.application.account.domain.Account;
import com.buying.back.application.account.helper.AccountCouponHelper;
import com.buying.back.application.account.repository.AccountRepository;
import com.buying.back.application.account.service.vo.AccountCouponVO;
import com.buying.back.application.account.service.vo.AccountDefaultVO;
import com.buying.back.application.account.service.vo.NormalAccountManagementVO;
import com.buying.back.application.common.dto.PagingDTO;
import com.buying.back.util.date.DateUtil;
import com.buying.back.util.email.HtmlEmailType;
import com.buying.back.util.email.provider.EmailProvider;
import com.buying.back.util.email.template.AccountEmailTemplate;
import com.buying.back.util.encryption.PasswordProvider;
import com.buying.back.util.string.RandomString;
import java.time.LocalDate;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AccountService {

  private final AccountRepository accountRepository;
  private final AccountCouponHelper accountCouponHelper;
  private final PasswordProvider passwordProvider;
  private final EmailProvider emailProvider;

  // auth
  @Transactional
  public AccountDefaultVO createNormalAccount(CreateAccountDTO dto) {
    Account account = accountRepository.findByEmail(dto.getEmail())
      .orElse(null);

    if (Objects.nonNull(account)) {
      // 비활성화된 계정이면서, 비활성화된지 1년 이내 이면 존재하는 아이디로 판단 -> 다시 활성화시킬 수 있음
      // 넘은 경우 완전히 삭제, 같은 이메일로 아이디 생성 불가능
      if (!account.isActivated() &&
        DateUtil.getDateDiff(account.getDeactivatedDate(), LocalDate.now()) < 366L) {
        throw new AccountException(AccountExceptionCode.DEACTIVATED_ACCOUNT);
      }
      throw new AccountException(AccountExceptionCode.ALREADY_EXIST_ACCOUNT);
    }

    dto.setPassword(passwordProvider.encode(dto.getPassword()));
    account = Account.initNormalAccount()
      .dto(dto)
      .build();
    accountRepository.save(account);

    return AccountDefaultVO.valueOf(account);
  }

  @Transactional
  public AccountDefaultVO updateAuthPassword(UpdateAuthPasswordDTO dto) {
    Account account = accountRepository.findByEmail(dto.getEmail())
      .orElseThrow(() -> new AccountException(AccountExceptionCode.NOT_FOUND_ACCOUNT));

    // TODO: 2023/04/16 code(인증 번호) 검증 필요

    if (dto.getConfirmPassword().equals(dto.getNewPassword())) {
      throw new AccountException(AccountExceptionCode.NOT_MATCHES_NEW_PASSWORD);
    }

    account.updatePassword(passwordProvider.encode(dto.getNewPassword()));
    accountRepository.save(account);

    return AccountDefaultVO.valueOf(account);
  }

  // login user
  public AccountDefaultVO getMyInformation(Long loginUserId) {
    Account account = accountRepository.findById(loginUserId)
      .orElseThrow(() -> new AccountException(AccountExceptionCode.NOT_FOUND_ACCOUNT));
    return AccountDefaultVO.valueOf(account);
  }

  public Page<AccountCouponVO> getMyCouponList(Long loginUserId, PagingDTO dto) {
    Account account = accountRepository.findById(loginUserId)
      .orElseThrow(() -> new AccountException(AccountExceptionCode.NOT_FOUND_ACCOUNT));
    return accountCouponHelper.getCouponListByAccount(dto, account);
  }

  @Transactional
  public AccountDefaultVO updateMyInformation(Long loginUserId, UpdateAccountDTO dto) {
    Account account = accountRepository.findById(loginUserId)
      .orElseThrow(() -> new AccountException(AccountExceptionCode.NOT_FOUND_ACCOUNT));

    account.updateInformation(dto);
    accountRepository.save(account);

    return AccountDefaultVO.valueOf(account);
  }

  @Transactional
  public AccountDefaultVO updateAccountActivate(Long loginUserId, UpdateActivateAccountDTO dto) {
    Account account = accountRepository.findById(loginUserId)
      .orElseThrow(() -> new AccountException(AccountExceptionCode.NOT_FOUND_ACCOUNT));

    if (account.isActivated() && dto.getActivated()) {
      throw new AccountException(AccountExceptionCode.ALREADY_ACTIVATED_ACCOUNT);
    }

    if (!account.isActivated() && !dto.getActivated()) {
      throw new AccountException(AccountExceptionCode.ALREADY_DEACTIVATED_ACCOUNT);
    }

    account.setActivated(dto.getActivated());
    accountRepository.save(account);

    return AccountDefaultVO.valueOf(account);
  }

  @Transactional
  public AccountDefaultVO updateAccountPassword(Long loginUserId, UpdateAccountPasswordDTO dto) {
    Account account = accountRepository.findById(loginUserId)
      .orElseThrow(() -> new AccountException(AccountExceptionCode.NOT_FOUND_ACCOUNT));

    if (!account.isActivated()) {
      throw new AccountException(AccountExceptionCode.DEACTIVATED_ACCOUNT);
    }

    if (dto.getConfirmPassword().equals(dto.getNewPassword())) {
      throw new AccountException(AccountExceptionCode.NOT_MATCHES_NEW_PASSWORD);
    }

    account.updatePassword(passwordProvider.encode(dto.getNewPassword()));
    accountRepository.save(account);

    return AccountDefaultVO.valueOf(account);
  }

  // management
  public Page<NormalAccountManagementVO> getNormalAccountList(SearchAccountManagementDTO dto) {
    return accountRepository.findAll(dto.getPageRequest(), dto);
  }

  public NormalAccountManagementVO getNormalAccountByManagement(Long accountId) {
    Account account = accountRepository.findById(accountId)
      .orElseThrow(() -> new AccountException(AccountExceptionCode.NOT_FOUND_ACCOUNT));
    return NormalAccountManagementVO.valueOf(account);
  }

  @Transactional
  public NormalAccountManagementVO activateNormalAccount(Long accountId, UpdateActivateAccountDTO dto) {
    Account account = accountRepository.findById(accountId)
      .orElseThrow(() -> new AccountException(AccountExceptionCode.NOT_FOUND_ACCOUNT));

    account.setActivated(dto.getActivated());
    accountRepository.save(account);
    return NormalAccountManagementVO.valueOf(account);
  }

  @Transactional
  public NormalAccountManagementVO resetNormalPassword(Long accountId) {
    Account account = accountRepository.findById(accountId)
      .orElseThrow(() -> new AccountException(AccountExceptionCode.NOT_FOUND_ACCOUNT));

    // TODO: 2022/12/18 비밀번호 변경 시 AWS 메일 전송
    String password = RandomString.generateRandomPassword();
    account.setPassword(passwordProvider.encode(password));

    emailProvider.send(
      new AccountEmailTemplate(HtmlEmailType.RESET_PASSWORD, account.getName(),
        account.getEmail(), password));

    accountRepository.save(account);
    return NormalAccountManagementVO.valueOf(account);
  }

}
