package com.buying.back.application.inquiry.repository;

import com.buying.back.application.inquiry.repository.param.SearchInquiryListParam;
import com.buying.back.application.inquiry.service.vo.InquiryManagementVO;
import com.buying.back.application.inquiry.service.vo.InquiryVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface InquiryRepositoryCustom {

  Page<InquiryVO> findAllByAccount(Pageable pageable, Long accountId);

  Page<InquiryManagementVO> findAllByManagement(Pageable pageable, SearchInquiryListParam param);

}
