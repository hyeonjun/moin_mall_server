package com.buying.back.application.inquiry.controller;

import com.buying.back.application.inquiry.controller.dto.ReplyInquiryDTO;
import com.buying.back.application.inquiry.service.InquiryService;
import com.buying.back.application.inquiry.service.vo.InquiryDetailVO;
import com.buying.back.util.response.CommonResponse;
import com.buying.back.util.response.CommonResponseCode;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/sys/inquiries")
@RequiredArgsConstructor
public class InquiryManagementController {

  private final InquiryService inquiryService;

  @PutMapping("/reply")
  public CommonResponse<InquiryDetailVO> replyToInquiry(
    @Valid @RequestBody ReplyInquiryDTO dto) {
    InquiryDetailVO vo = inquiryService.replyToInquiry(dto);
    return new CommonResponse<>(vo, CommonResponseCode.SUCCESS);
  }

}
