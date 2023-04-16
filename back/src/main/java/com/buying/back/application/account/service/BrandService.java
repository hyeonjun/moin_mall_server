package com.buying.back.application.account.service;

import com.buying.back.application.account.code.exception.AccountException;
import com.buying.back.application.account.code.exception.AccountException.AccountExceptionCode;
import com.buying.back.application.account.code.exception.BrandException;
import com.buying.back.application.account.code.exception.BrandException.BrandExceptionCode;
import com.buying.back.application.account.code.type.RoleType;
import com.buying.back.application.account.controller.dto.account.UpdateAccountDTO;
import com.buying.back.application.account.controller.dto.brand.CreateBrandCrewAccountDTO;
import com.buying.back.application.account.controller.dto.brand.CreateBrandAdminAccountDTO;
import com.buying.back.application.account.controller.dto.brand.UpdateBrandActivateDTO;
import com.buying.back.application.account.controller.dto.brand.UpdateBrandInfoDTO;
import com.buying.back.application.account.controller.dto.management.SearchBrandEnterpriseManagementDTO;
import com.buying.back.application.account.domain.Account;
import com.buying.back.application.account.domain.Brand;
import com.buying.back.application.account.helper.BrandAccountHelper;
import com.buying.back.application.account.repository.AccountRepository;
import com.buying.back.application.account.repository.BrandRepository;
import com.buying.back.application.account.service.vo.BrandAccountDetailVO;
import com.buying.back.application.account.service.vo.BrandAccountManagementVO;
import com.buying.back.application.account.service.vo.BrandDetailVO;
import com.buying.back.application.account.service.vo.BrandEnterpriseManagementVO;
import com.buying.back.application.common.dto.PagingDTO;
import com.buying.back.util.encryption.PasswordProvider;
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
public class BrandService {
  private final BrandRepository brandRepository;
  private final AccountRepository accountRepository;
  private final BrandAccountHelper brandAccountHelper;

  private final PasswordProvider passwordProvider;

  @Transactional
  public BrandDetailVO createBrandEnterprise(CreateBrandAdminAccountDTO dto) {
    Brand brand = brandRepository.findByBusinessNumber(dto.getBusinessNumber())
      .orElse(null);

    if (Objects.nonNull(brand)) {
      throw new BrandException(BrandExceptionCode.ALREADY_EXIST_BUSINESS_NUMBER);
    }

    dto.setBrandPassword(passwordProvider.encode(dto.getBrandPassword()));
    brand = Brand.initBrand()
      .dto(dto)
      .build();

    brandRepository.save(brand);
    Account account = brandAccountHelper.createBrandAdminAccount(dto, brand, RoleType.BRAND_ADMIN);

    return BrandAccountDetailVO.valueOf(brand, account);
  }

  @Transactional
  public BrandAccountDetailVO createBrandCrewAccount(CreateBrandCrewAccountDTO dto) {
    Brand brand = brandRepository.findByBusinessNumber(dto.getBusinessNumber())
      .orElseThrow(() -> new BrandException(BrandExceptionCode.NOT_FOUND_BRAND));

    if (!passwordProvider.matches(dto.getBrandPassword(), brand.getPassword())) {
      throw new BrandException(BrandExceptionCode.NOT_MATCH_BRAND_PASSWORD);
    }

    Account account = brandAccountHelper.createBrandAdminAccount(dto, brand, RoleType.BRAND_CREW);

    return BrandAccountDetailVO.valueOf(brand, account);
  }

  public BrandAccountDetailVO getBrandAccountMyInformation(Long brandId, Long accountId) {
    Brand brand = brandRepository.findById(brandId)
      .orElseThrow(() -> new BrandException(BrandExceptionCode.NOT_FOUND_BRAND));

    Account account = accountRepository.findById(accountId)
      .orElseThrow(() -> new AccountException(AccountExceptionCode.NOT_FOUND_ACCOUNT));

    return BrandAccountDetailVO.valueOf(brand, account);
  }

  @Transactional
  public BrandAccountDetailVO updateBrandAccountMyInformation(
    Long brandId, Long accountId, UpdateAccountDTO dto) {
    Brand brand = brandRepository.findById(brandId)
      .orElseThrow(() -> new BrandException(BrandExceptionCode.NOT_FOUND_BRAND));

    if (!brand.isActivated()) {
      throw new BrandException(BrandExceptionCode.ALREADY_DEACTIVATED_BRAND);
    }

    Account account = accountRepository.findById(accountId)
      .orElseThrow(() -> new AccountException(AccountExceptionCode.NOT_FOUND_ACCOUNT));

    if (!account.isActivated()) {
      throw new AccountException(AccountExceptionCode.ALREADY_DEACTIVATED_ACCOUNT);
    }

    account.updateInformation(dto);
    accountRepository.save(account);

    return BrandAccountDetailVO.valueOf(brand, account);
  }

  @Transactional
  public void updateBrandAccountActivate(
    Long brandId, Long accountId, UpdateBrandActivateDTO dto) {
    Brand brand = brandRepository.findById(brandId)
      .orElseThrow(() -> new BrandException(BrandExceptionCode.NOT_FOUND_BRAND));

    Account account = accountRepository.findById(accountId)
      .orElseThrow(() -> new AccountException(AccountExceptionCode.NOT_FOUND_ACCOUNT));

    // brand 비활성화, 변경 - 활성화하려고 할 때, admin만 활성화 가능
    if (!brand.isActivated() && dto.getActivated() && !RoleType.BRAND_ADMIN.equals(account.getRoleType())) {
      throw new BrandException(BrandExceptionCode.ALREADY_ACTIVATED_BRAND);
    }

    // admin 비활성화 -> brand 비활성화 -> crew 비활성화
    // crew 비활성화

    // admin 활성화 -> brand 활성화 api 호출
    // crew 활성화 -> brand 활성화 여부 확인
    if (Boolean.FALSE.equals(dto.getActivated()) && RoleType.BRAND_ADMIN.equals(account.getRoleType())) {
      brand.setActivated(Boolean.FALSE);
      brandRepository.save(brand);

      // brand accounts 비활성화
      brandAccountHelper.updateBrandAccountDeactivate(brand);
    } else {
      brandAccountHelper.updateAccountActivate(account, dto.getActivated());
    }
  }

  @Transactional
  public BrandDetailVO updateBrandEnterpriseInfo(Long brandId, Long accountId, UpdateBrandInfoDTO dto) {
    Brand brand = brandRepository.findById(brandId)
      .orElseThrow(() -> new BrandException(BrandExceptionCode.NOT_FOUND_BRAND));

    if (!brand.isActivated()) {
      throw new BrandException(BrandExceptionCode.ALREADY_DEACTIVATED_BRAND);
    }

    Account account = accountRepository.findById(accountId)
      .orElseThrow(() -> new AccountException(AccountExceptionCode.NOT_FOUND_ACCOUNT));

    if (!account.isActivated() || !RoleType.BRAND_ADMIN.equals(account.getRoleType())) {
      throw new AccountException(AccountExceptionCode.ALREADY_DEACTIVATED_ACCOUNT);
    }

    if (!passwordProvider.matches(dto.getConfirmBrandPassword(), brand.getPassword())) {
      throw new BrandException(BrandExceptionCode.NOT_MATCH_BRAND_PASSWORD);
    }

    brand.update(dto);
    brandRepository.save(brand);

    return BrandDetailVO.valueOf(brand);
  }

  @Transactional
  public void updateBrandDeactivate(Long brandId, Long accountId) {
    Brand brand = brandRepository.findById(brandId)
      .orElseThrow(() -> new BrandException(BrandExceptionCode.NOT_FOUND_BRAND));

    if (!brand.isActivated()) {
      throw new BrandException(BrandExceptionCode.ALREADY_DEACTIVATED_BRAND);
    }

    Account account = accountRepository.findById(accountId)
      .orElseThrow(() -> new AccountException(AccountExceptionCode.NOT_FOUND_ACCOUNT));

    if (!RoleType.BRAND_ADMIN.equals(account.getRoleType())) {
      throw new AccountException(AccountExceptionCode.NOT_AUTHORIZED);
    }

    // brand 비활성화 -> admin, crew 비활성화
    brand.setActivated(Boolean.FALSE);
    brandRepository.save(brand);

    // brand accounts 비활성화
    brandAccountHelper.updateBrandAccountDeactivate(brand);
  }

  // management
  public Page<BrandEnterpriseManagementVO> getBrandEnterpriseList(SearchBrandEnterpriseManagementDTO dto) {
    return brandRepository.findAllEnterprise(dto.getPageRequest(), dto);
  }

  public Page<BrandAccountManagementVO> getBrandAccountList(Long brandId, PagingDTO dto) {
    return brandRepository.findAllAccountByBrandId(dto.getPageRequest(), brandId);
  }

}
