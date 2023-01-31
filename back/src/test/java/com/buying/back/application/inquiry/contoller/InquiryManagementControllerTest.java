package com.buying.back.application.inquiry.contoller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.subsectionWithPath;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.buying.back.application.account.code.type.RoleType;
import com.buying.back.application.account.repository.AccountRepository;
import com.buying.back.application.inquiry.code.type.BrandInquiryGroupType;
import com.buying.back.application.inquiry.code.type.InquiryParentType;
import com.buying.back.application.inquiry.code.type.NormalInquiryGroupType;
import com.buying.back.application.inquiry.controller.InquiryManagementController;
import com.buying.back.application.inquiry.controller.dto.management.SearchInquiryManagementDTO;
import com.buying.back.application.inquiry.service.InquiryService;
import com.buying.back.application.inquiry.service.vo.InquiryVO;
import com.buying.back.application.mock.inquiry.InquiryMockDTO;
import com.buying.back.util.encryption.PasswordProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

@ExtendWith({SpringExtension.class})
@WebMvcTest(InquiryManagementController.class)
@Import(InquiryManagementController.class)
@AutoConfigureRestDocs
@ContextConfiguration(classes = {
  MockSecurityConfig.class,
})
public class InquiryManagementControllerTest {

  private final String URI_TEMPLATE = "/api/v1/sys/inquiries";
  private MockMvc mockMvc;

  @MockBean
  private InquiryService inquiryService;

  @Autowired
  private AccountRepository accountRepository;
  @Autowired
  private PasswordProvider passwordProvider;
  @Autowired
  private ObjectMapper objectMapper;

  @BeforeEach
  public void setUp(WebApplicationContext webApplicationContext) {

    Authentication auth = new MockAuthProvider(accountRepository, passwordProvider)
      .authenticate("test@test.com", "1234", RoleType.ADMIN);
    SecurityContextHolder.getContext().setAuthentication(auth);

    this.mockMvc = MockMvcBuilders
      .webAppContextSetup(webApplicationContext)
      .build();
  }

  @Test
  void getInquiryListTest() throws Exception {
    given(inquiryService
      .getInquiryList(any(SearchInquiryManagementDTO.class)))
      .willAnswer(invocation -> {
        List<InquiryVO> list = Collections
          .singletonList(new InquiryVO());
        return new PageImpl<>(list);
      });

//    String value = objectMapper.writeValueAsString(NormalInquiryGroupType.MEMBER);

    MultiValueMap<String, String> params =
      ParamConvertUtil.convert(InquiryMockDTO.searchInquiryManagementDTO());
//    params.put("inquiryParentType", List.of("Normal", "MEMBER"));
//    params.put("inquiryChildType", List.of("MEMBER", "MEMBER_INFO"));

    this.mockMvc
      .perform(RestDocumentationRequestBuilders
        .get(URI_TEMPLATE)
        .params(params)
        .contentType(MediaType.APPLICATION_JSON))
      .andDo(print())
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON));
//      .andExpect(jsonPath("$.data.content").isArray());
  }

}
