package com.buying.back.application.inquiry.service;

import static org.springframework.util.StringUtils.hasText;

import com.buying.back.application.account.code.exception.AccountException;
import com.buying.back.application.account.code.exception.AccountException.AccountExceptionCode;
import com.buying.back.application.account.domain.Account;
import com.buying.back.application.account.repository.AccountRepository;
import com.buying.back.application.common.dto.PagingDTO;
import com.buying.back.application.inquiry.code.exception.InquiryException;
import com.buying.back.application.inquiry.code.exception.InquiryException.InquiryExceptionCode;
import com.buying.back.application.inquiry.code.type.InquiryChildType;
import com.buying.back.application.inquiry.code.type.InquiryParentType;
import com.buying.back.application.inquiry.code.type.NormalInquiryGroupType;
import com.buying.back.application.inquiry.controller.dto.CreateInquiryDTO;
import com.buying.back.application.inquiry.controller.dto.ReplyInquiryDTO;
import com.buying.back.application.inquiry.controller.dto.UpdateInquiryDTO;
import com.buying.back.application.inquiry.domain.Inquiry;
import com.buying.back.application.inquiry.repository.InquiryRepository;
import com.buying.back.application.inquiry.service.vo.InquiryDetailVO;
import com.buying.back.application.inquiry.service.vo.InquiryVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
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
  public InquiryDetailVO createNormalInquiry(Long accountId, CreateInquiryDTO dto) {
    Account author = accountRepository.findById(accountId)
      .orElseThrow(() -> new AccountException(AccountExceptionCode.NOT_FOUND_ACCOUNT));

    if (validateInquiryType(dto.getInquiryParentType(), dto.getInquiryChildType())) {
      throw new InquiryException(InquiryExceptionCode.WRONG_INQUIRY_TYPE);
    }

    // TODO: 2023/01/08 사진 저장

    Inquiry inquiry = Inquiry.initInquiry()
      .dto(dto)
      .author(author)
      .build();

    inquiryRepository.save(inquiry);
    return InquiryDetailVO.valueOf(author, inquiry);
  }

  public Page<InquiryVO> getMyInquiryList(Long accountId, PagingDTO dto) {
    Account author = accountRepository.findById(accountId)
      .orElseThrow(() -> new AccountException(AccountExceptionCode.NOT_FOUND_ACCOUNT));

    return inquiryRepository.findAllByAccount(dto.getPageRequest(), author.getId());
  }

  public InquiryDetailVO getMyInquiryDetail(Long accountId, Long inquiryId) {
    Inquiry inquiry = inquiryRepository.findById(inquiryId)
      .orElseThrow(() -> new InquiryException(InquiryExceptionCode.NOT_FOUND_INQUIRY));

    if (!inquiry.getAuthor().getId().equals(accountId) || inquiry.isDeleted()) {
      throw new InquiryException(InquiryExceptionCode.NOT_AUTHORIZED);
    }

    return InquiryDetailVO.valueOf(inquiry);
  }

  @Transactional
  public InquiryDetailVO updateMyInquiry(Long accountId, Long inquiryId, UpdateInquiryDTO dto) {
    Inquiry inquiry = inquiryRepository.findById(inquiryId)
      .orElseThrow(() -> new InquiryException(InquiryExceptionCode.NOT_FOUND_INQUIRY));

    if (!inquiry.getAuthor().getId().equals(accountId) || inquiry.isDeleted()) {
      throw new InquiryException(InquiryExceptionCode.NOT_AUTHORIZED);
    }

    if (validateInquiryType(dto.getInquiryParentType(), dto.getInquiryChildType())) {
      throw new InquiryException(InquiryExceptionCode.WRONG_INQUIRY_TYPE);
    }

    inquiry.updateInquiry(dto);

    inquiryRepository.save(inquiry);
    return InquiryDetailVO.valueOf(inquiry);
  }

  @Transactional
  public void deleteMyInquiry(Long accountId, Long inquiryId) {
    Inquiry inquiry = inquiryRepository.findById(inquiryId)
      .orElseThrow(() -> new InquiryException(InquiryExceptionCode.NOT_FOUND_INQUIRY));

    if (!inquiry.getAuthor().getId().equals(accountId)) {
      throw new InquiryException(InquiryExceptionCode.NOT_AUTHORIZED);
    }

    if (inquiry.isDeleted()) {
      throw new InquiryException(InquiryExceptionCode.ALREADY_DELETED);
    }

    inquiry.setDeleted(true);
    inquiryRepository.save(inquiry);
  }

  @Transactional
  public InquiryDetailVO replyToInquiry(ReplyInquiryDTO dto) {
    Inquiry inquiry = inquiryRepository.findById(dto.getInquiryId())
      .orElseThrow(() -> new InquiryException(InquiryExceptionCode.NOT_FOUND_INQUIRY));

    if (inquiry.isDeleted()) {
      throw new InquiryException(InquiryExceptionCode.ALREADY_DELETED);
    }

    if (hasText(inquiry.getAnswer())) {
      throw new InquiryException(InquiryExceptionCode.ALREADY_REPLY_ANSWER);
    }

    inquiry.replyInquiry(dto);
    inquiryRepository.save(inquiry);

    return InquiryDetailVO.valueOf(inquiry);
  }

  private boolean validateInquiryType(InquiryParentType inquiryParentType,
    InquiryChildType inquiryChildType) {
    if (!(inquiryParentType instanceof NormalInquiryGroupType)) {
      return true;
    }

    return !inquiryParentType.childCheck(inquiryChildType);
  }

}
