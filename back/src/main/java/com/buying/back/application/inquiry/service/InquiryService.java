package com.buying.back.application.inquiry.service;

import com.buying.back.application.account.code.exception.AccountException;
import com.buying.back.application.account.code.exception.AccountException.AccountExceptionCode;
import com.buying.back.application.account.domain.Account;
import com.buying.back.application.account.repository.AccountRepository;
import com.buying.back.application.inquiry.code.exception.InquiryException;
import com.buying.back.application.inquiry.code.exception.InquiryException.InquiryExceptionCode;
import com.buying.back.application.inquiry.controller.dto.CreateInquiryDTO;
import com.buying.back.application.inquiry.domain.Inquiry;
import com.buying.back.application.inquiry.repository.InquiryRepository;
import com.buying.back.application.inquiry.service.vo.InquiryVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class InquiryService {

  private final AccountRepository accountRepository;
  private final InquiryRepository inquiryRepository;

  @Transactional
  public InquiryVO createInquiry(CreateInquiryDTO dto) {
    Account author = accountRepository.findById(dto.getAccountId())
      .orElseThrow(() -> new AccountException(AccountExceptionCode.NOT_FOUND_ACCOUNT));

    // TODO: 2023/01/08 account 유형이 일반 유저가 맞는지 체크

    if (!dto.getInquiryParentType().childCheck(dto.getInquiryChildType())) {
      throw new InquiryException(InquiryExceptionCode.WRONG_INQUIRY_TYPE);
    }

    // TODO: 2023/01/08 사진 저장

    Inquiry inquiry = Inquiry.initInquiry()
      .dto(dto)
      .author(author)
      .build();

    inquiryRepository.save(inquiry);
    return InquiryVO.valueOf(author, inquiry);
  }

}
