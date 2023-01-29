package com.buying.back.application.inquiry.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import com.buying.back.application.account.domain.Account;
import com.buying.back.application.account.repository.AccountRepository;
import com.buying.back.application.inquiry.code.exception.InquiryException;
import com.buying.back.application.inquiry.controller.dto.CreateInquiryDTO;
import com.buying.back.application.inquiry.repository.InquiryRepository;
import com.buying.back.application.inquiry.service.vo.InquiryVO;
import com.buying.back.application.mock.inquiry.InquiryMockDTO;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("local")
@ExtendWith(MockitoExtension.class)
class InquiryServiceTest {

  @Spy
  @InjectMocks
  private InquiryService inquiryService;

  @Mock
  private InquiryRepository inquiryRepository;
  @Mock
  private AccountRepository accountRepository;

  @DisplayName("일반 문의사항 생성 - 성공")
  @Test
  void createInquiry_SUCCESS() {
    CreateInquiryDTO dto = InquiryMockDTO.createInquiryDTO(true);

    Account account = mock(Account.class);
    given(accountRepository.findById(anyLong())).willReturn(Optional.ofNullable(account));

    InquiryVO vo = inquiryService.createInquiry(1L, dto);

    assertEquals(dto.getTitle(), vo.getTitle());
    assertEquals(dto.getContent(), vo.getContent());
    assertEquals(dto.getInquiryParentType().getParentCode(), vo.getInquiryParentType());
    assertEquals(dto.getInquiryChildType().getChildCode(), vo.getInquiryChildType());

  }

  @DisplayName("일반 문의사항 생성 - 잘못된 문의사항 유형 예외")
  @Test
  void createInquiry_WRONG_INQUIRY_TYPE_EXCEPTION() {
    CreateInquiryDTO dto = InquiryMockDTO.createInquiryDTO(false);

    Account account = mock(Account.class);
    given(accountRepository.findById(anyLong())).willReturn(Optional.ofNullable(account));

    assertThrows(InquiryException.class, () -> {
      inquiryService.createInquiry(1L, dto);
    });
  }
}