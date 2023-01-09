package com.buying.back.application.inquiry.controller;

import static com.buying.back.util.response.CommonResponseCode.SUCCESS;

import com.buying.back.application.inquiry.controller.dto.CreateInquiryDTO;
import com.buying.back.application.inquiry.controller.dto.CreateNormalInquiryDTO;
import com.buying.back.application.inquiry.service.InquiryService;
import com.buying.back.application.inquiry.service.vo.InquiryVO;
import com.buying.back.util.response.CommonResponse;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/pub/inquiries")
@RequiredArgsConstructor
public class NormalInquiryController {

  private final InquiryService inquiryService;

  @PostMapping()
  public CommonResponse<InquiryVO> createInquiry(@Valid @RequestBody CreateInquiryDTO dto) {
    InquiryVO vo = inquiryService.createInquiry(dto);
    return new CommonResponse<>(vo, SUCCESS);
  }

}
