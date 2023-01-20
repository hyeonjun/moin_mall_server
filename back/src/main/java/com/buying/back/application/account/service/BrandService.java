package com.buying.back.application.account.service;

import com.buying.back.application.account.code.exception.AccountException;
import com.buying.back.application.account.controller.dto.CreateAccountDTO;
import com.buying.back.application.account.controller.dto.CreateBrandDTO;
import com.buying.back.application.account.domain.Account;
import com.buying.back.application.account.domain.Brand;
import com.buying.back.application.account.repository.AccountRepository;
import com.buying.back.application.account.repository.BrandRepository;
import com.buying.back.application.account.service.vo.AccountDefaultVO;
import com.buying.back.util.encryption.PasswordProvider;
import com.buying.back.util.string.RandomString;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;


@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BrandService {
    private final BrandRepository brandRepository;
    private final AccountRepository accountRepository;

    private final PasswordProvider passwordProvider;
    @Transactional
    public AccountDefaultVO createAccount(CreateBrandDTO dto) {
        Brand brand = brandRepository.findByCompanyRegistrationNumber(dto.getCompanyRegistrationNumber())
                .orElse(null);

        if (Objects.isNull(brand)) {
            brand = Brand.init()
                    .dto(dto)
                    .build();
            brandRepository.save(brand);
        }

        Account account = accountRepository.findByEmail(dto.getEmail())
                .orElse(null);

        if (Objects.nonNull(account)) {
            throw new AccountException(AccountException.AccountExceptionCode.ALREADY_EXIST_ACCOUNT);
        }

        String randomPassword = RandomString.generateRandomPassword();
        dto.setPassword(passwordProvider.encode(randomPassword));

        account = Account.initBrand()
                .dto(dto)
                .brand(brand)
                .build();
        accountRepository.save(account);

        // Todo : 이메일로 초대 메일 발송 추가 필요, 임시 비밀번호 같이 발송

        return new AccountDefaultVO(account);
    }

    @Transactional
    public AccountDefaultVO essentialPasswordChange(CreateAccountDTO dto) {
        Account account = accountRepository.findByEmail(dto.getEmail())
                .orElse(null);

        String newPassword = passwordProvider.encode(dto.getPassword());
        if(account.getPassword().equals(newPassword)){
            throw new AccountException(AccountException.AccountExceptionCode.NOT_CHANGE_PASSWORD);
        }

        account.setPassword(newPassword);
        account.setActivated(true);
        account.setBirthDay(dto.getBirthDay());

        return new AccountDefaultVO(account);
    }
}
