package com.buying.back.application.inquiry.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.buying.back.application.common.dto.PagingDTO;
import com.buying.back.application.inquiry.code.type.NormalInquiryGroupType;
import com.buying.back.application.inquiry.code.type.NormalInquiryMemberType;
import com.buying.back.application.inquiry.controller.dto.common.SearchInquiryNormalDTO;
import com.buying.back.application.inquiry.controller.dto.management.SearchInquiryManagementDTO;
import com.buying.back.application.inquiry.repository.param.SearchInquiryListParam;
import com.buying.back.application.inquiry.service.vo.InquiryManagementVO;
import com.buying.back.application.inquiry.service.vo.InquiryVO;
import com.buying.back.application.mock.inquiry.InquiryMockDTO;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("local")
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class InquiryRepositoryTest {

  @Autowired
  private InquiryRepository inquiryRepository;

  @Test
  void findAllByAccount() {
    SearchInquiryNormalDTO dto = InquiryMockDTO.searchInquiryNormalDTO();
    SearchInquiryListParam param = SearchInquiryListParam.valueOf(dto);
    param.setDeleted(false);
    param.setAuthorId(1L);

    Page<InquiryVO> page = inquiryRepository.findAllByAccount(dto.getPageRequest(), param);
    /*
    select
        account1_.account_id as col_0_0_,
        account1_.name as col_1_0_,
        inquiry0_.inquiry_id as col_2_0_,
        inquiry0_.created_date_time as col_3_0_,
        inquiry0_.updated_date_time as col_4_0_,
        inquiry0_.title as col_5_0_,
        inquiry0_.inquiry_parent_type as col_6_0_,
        inquiry0_.answer is not null as col_7_0_
    from
        inquiry inquiry0_
    inner join
        account account1_
            on inquiry0_.inquiry_author_id=account1_.account_id
    where
        inquiry0_.created_date_time>=?
        and inquiry0_.created_date_time<?
        and inquiry0_.deleted=?
        and (
            inquiry0_.answer is null
        )
        and inquiry0_.inquiry_parent_type=?
        and inquiry0_.inquiry_child_type=?
        and account1_.account_id=?
    order by
        inquiry0_.inquiry_id desc limit ?
     */
    assertNotNull(page);
  }

  @Test
  void findAllByManagement() {
    SearchInquiryManagementDTO dto = InquiryMockDTO.searchInquiryManagementDTO();

    Page<InquiryManagementVO> page = inquiryRepository.findAllByManagement(
      dto.getPageRequest(),
      SearchInquiryListParam.valueOf(dto));
    /*
    select
        account1_.account_id as col_0_0_,
        account1_.name as col_1_0_,
        inquiry0_.inquiry_id as col_2_0_,
        inquiry0_.created_date_time as col_3_0_,
        inquiry0_.updated_date_time as col_4_0_,
        inquiry0_.title as col_5_0_,
        inquiry0_.inquiry_parent_type as col_6_0_,
        inquiry0_.answer is not null as col_7_0_,
        inquiry0_.deleted as col_8_0_
    from
        inquiry inquiry0_
    inner join
        account account1_
            on inquiry0_.inquiry_author_id=account1_.account_id
    where
        inquiry0_.created_date_time>=?
        and inquiry0_.created_date_time<?
        and inquiry0_.deleted=?
        and (
            inquiry0_.answer is null
        )
        and inquiry0_.inquiry_parent_type=?
        and inquiry0_.inquiry_child_type=?
        and (
            account1_.email like ? escape '!'
        )
    order by
        inquiry0_.inquiry_id desc limit ?
     */

    assertNotNull(page);
  }

}
