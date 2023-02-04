package com.buying.back.application.inquiry.contoller.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.buying.back.application.inquiry.code.type.InquiryChildType;
import com.buying.back.application.inquiry.code.type.InquiryParentType;
import com.buying.back.application.inquiry.code.type.NormalInquiryGroupType;
import com.buying.back.application.inquiry.code.type.NormalInquiryMemberType;
import com.buying.back.application.inquiry.controller.dto.common.CreateInquiryDTO;
import com.buying.back.application.inquiry.controller.dto.management.SearchInquiryManagementDTO;
import com.buying.back.application.mock.inquiry.InquiryMockDTO;
import com.buying.back.infra.config.ObjectMapperConfig;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = ObjectMapperConfig.class)
public class InquiryDtoObjectMapperTest {

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  void createInquiryDtoTest() throws JsonProcessingException {
    CreateInquiryDTO dto = InquiryMockDTO.createInquiryDTO(true);
    String json = objectMapper.writeValueAsString(dto);
    System.out.println(json);

    CreateInquiryDTO dto2 = objectMapper.readValue(json, CreateInquiryDTO.class);
    InquiryParentType inquiryParentType = dto2.getInquiryParentType();
    InquiryChildType inquiryChildType = dto2.getInquiryChildType();

    assertEquals(inquiryParentType, NormalInquiryGroupType.MEMBER);
    assertEquals(inquiryChildType, NormalInquiryMemberType.MEMBER_INFO);
  }

  @Test
  void SearchInquiryManagementDtoTest() throws JsonProcessingException {
    SearchInquiryManagementDTO dto = InquiryMockDTO.searchInquiryManagementDTO();
    String json = objectMapper.writeValueAsString(dto);
    System.out.println(json);

    SearchInquiryManagementDTO dto2 = objectMapper.readValue(json, SearchInquiryManagementDTO.class);
    InquiryParentType inquiryParentType = dto2.getInquiryParentType();
    InquiryChildType inquiryChildType = dto2.getInquiryChildType();

    assertEquals(inquiryParentType, NormalInquiryGroupType.MEMBER);
    assertEquals(inquiryChildType, NormalInquiryMemberType.MEMBER_INFO);
  }

}
