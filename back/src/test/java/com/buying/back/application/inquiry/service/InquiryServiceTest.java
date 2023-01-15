package com.buying.back.application.inquiry.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.buying.back.application.account.domain.Account;
import com.buying.back.application.account.repository.AccountRepository;
import com.buying.back.application.common.dto.PagingDTO;
import com.buying.back.application.inquiry.code.exception.InquiryException;
import com.buying.back.application.inquiry.controller.dto.CreateInquiryDTO;
import com.buying.back.application.inquiry.controller.dto.UpdateInquiryDTO;
import com.buying.back.application.inquiry.domain.Inquiry;
import com.buying.back.application.inquiry.repository.InquiryRepository;
import com.buying.back.application.inquiry.service.vo.InquiryVO;
import com.buying.back.application.mock.inquiry.InquiryMockDTO;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("local")
@ExtendWith(MockitoExtension.class)
class InquiryServiceTest {

  @InjectMocks
  private InquiryService inquiryService;

  @Spy
  private InquiryRepository inquiryRepository;
  @Spy
  private AccountRepository accountRepository;

  @DisplayName("일반 문의사항 생성 - 성공")
  @Test
  void createNormalInquiryTest_SUCCESS() {
    CreateInquiryDTO dto = InquiryMockDTO.createInquiryDTO(true);

    Account account = mock(Account.class);
    given(accountRepository.findById(anyLong())).willReturn(Optional.ofNullable(account));

    InquiryVO vo = inquiryService.createNormalInquiry(1L, dto);

    verify(inquiryRepository).save(any());

    assertEquals(dto.getTitle(), vo.getTitle());
    assertEquals(dto.getContent(), vo.getContent());
    assertEquals(dto.getInquiryParentType().getParentCode(), vo.getInquiryParentType());
    assertEquals(dto.getInquiryChildType().getChildCode(), vo.getInquiryChildType());

  }

  @DisplayName("일반 문의사항 생성 - 잘못된 문의사항 유형 예외")
  @Test
  void createNormalInquiryTest_WRONG_INQUIRY_TYPE_EXCEPTION() {
    CreateInquiryDTO dto = InquiryMockDTO.createInquiryDTO(false);

    Account account = mock(Account.class);
    given(accountRepository.findById(anyLong())).willReturn(Optional.ofNullable(account));

    assertThrows(InquiryException.class, () -> {
      inquiryService.createNormalInquiry(1L, dto);
    });
  }

  @DisplayName("일반 문의사항 페이징 조회")
  @Test
  void getInquiryMyListTest() {
    PagingDTO dto = new PagingDTO();

    Account account = mock(Account.class);
    given(accountRepository.findById(anyLong())).willReturn(Optional.ofNullable(account));
    given(inquiryRepository.findAllByAccount(any(), anyLong()))
      .willAnswer(invocation -> {
        List<InquiryVO> list = List.of(new InquiryVO());
        return new PageImpl<>(list);
    });

    Page<InquiryVO> vo = inquiryService.getMyInquiryList(1L, dto);
    assertNotNull(vo);
  }

  @DisplayName("일반 문의사항 상세 조회")
  @Test
  void getMyInquiryDetailTest() {
    Long accountId = 1L;
    Long inquiryId = 1L;

    Inquiry inquiry = mock(Inquiry.class);
    given(inquiryRepository.findById(anyLong())).willReturn(Optional.ofNullable(inquiry));

    Account account = mock(Account.class);
    assert inquiry != null;
    given(inquiry.getAuthor()).willReturn(account);
    given(account.getId()).willReturn(accountId);

    InquiryVO vo = inquiryService.getMyInquiryDetail(accountId, inquiryId);
    assertNotNull(vo);
  }

  @DisplayName("일반 문의사항 수정")
  @Test
  void updateMyInquiryTest() {
    Long accountId = 1L;
    Long inquiryId = 1L;
    UpdateInquiryDTO dto = InquiryMockDTO.updateInquiryDTO(true);

    Inquiry inquiry = mock(Inquiry.class);
    given(inquiryRepository.findById(anyLong())).willReturn(Optional.ofNullable(inquiry));

    Account account = mock(Account.class);
    assert inquiry != null;
    given(inquiry.getAuthor()).willReturn(account);
    given(account.getId()).willReturn(accountId);

    InquiryVO vo = inquiryService.updateMyInquiry(accountId, inquiryId, dto);

    verify(inquiryRepository).save(any());
  }

  @DisplayName("일반 문의사항 삭제")
  @Test
  void deleteMyInquiryTest() {
    Long accountId = 1L;
    Long inquiryId = 1L;

    Inquiry inquiry = mock(Inquiry.class);
    given(inquiryRepository.findById(anyLong())).willReturn(Optional.ofNullable(inquiry));

    Account account = mock(Account.class);
    assert inquiry != null;
    given(inquiry.getAuthor()).willReturn(account);
    given(account.getId()).willReturn(accountId);

    inquiryService.deleteMyInquiry(accountId, inquiryId);

    verify(inquiryRepository).save(any());
  }
}