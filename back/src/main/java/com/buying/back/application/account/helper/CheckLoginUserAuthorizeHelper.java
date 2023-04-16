package com.buying.back.application.account.helper;

import com.buying.back.application.account.code.exception.BrandException;
import com.buying.back.application.account.domain.Brand;
import com.buying.back.application.account.repository.BrandRepository;
import com.buying.back.application.common.exception.code.AuthenticationException;
import com.buying.back.application.common.exception.code.AuthenticationException.AuthenticationExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CheckLoginUserAuthorizeHelper {

  public void checkBrandAuthority(Brand originBrand, Brand targetBrand) {
    if (!originBrand.equals(targetBrand)) {
      throw new AuthenticationException(AuthenticationExceptionCode.NOT_AUTHORIZED);
    }
  }

}
