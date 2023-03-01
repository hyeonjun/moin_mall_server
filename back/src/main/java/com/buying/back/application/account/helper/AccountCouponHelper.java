package com.buying.back.application.account.helper;

import com.buying.back.application.account.code.exception.AccountCouponException;
import com.buying.back.application.account.code.exception.AccountCouponException.AccountCouponExceptionCode;
import com.buying.back.application.account.domain.Account;
import com.buying.back.application.account.domain.AccountCouponRelation;
import com.buying.back.application.account.repository.AccountCouponRelationRepository;
import com.buying.back.application.account.repository.AccountRepository;
import com.buying.back.application.account.service.vo.AccountCouponVO;
import com.buying.back.application.common.dto.PagingDTO;
import com.buying.back.application.coupon.domain.Coupon;
import com.buying.back.application.coupon.repository.CouponRepository;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class AccountCouponHelper {

  private final AccountRepository accountRepository;
  private final CouponRepository couponRepository;
  private final AccountCouponRelationRepository accountCouponRelationRepository;

  @Transactional
  public void assignCoupon(Account account, Coupon coupon) {
    AccountCouponRelation relation = accountCouponRelationRepository
      .findByAccountAndCoupon(account, coupon)
      .orElse(null);

    if (Objects.nonNull(relation)) {
      throw new AccountCouponException(AccountCouponExceptionCode.ALREADY_GRANTED_COUPON_ACCOUNT);
    }

    relation = new AccountCouponRelation(account, coupon);
    accountCouponRelationRepository.save(relation);
  }

  public Page<AccountCouponVO> getCouponListByAccount(PagingDTO dto, Account account) {
    return couponRepository.findAllAccountCoupon(dto.getPageRequest(), account);
  }

}
